package com.orion.mdd.controllers;

import com.orion.mdd.dtos.api.ApiResponse;
import com.orion.mdd.dtos.topic.TopicDetailsDto;
import com.orion.mdd.mappers.AbstractPostMapper;
import com.orion.mdd.mappers.AbstractTopicDetailMapper;
import com.orion.mdd.models.Topic;
import com.orion.mdd.models.User;
import com.orion.mdd.services.PostService;
import com.orion.mdd.services.TopicService;
import com.orion.mdd.services.UserService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/topics/")
@Tag(name = "Topics", description = "The Topics API. Contains all the operations that can be performed on topics.")
public class TopicController {

    Logger logger = LoggerFactory.getLogger(TopicController.class);

    @Autowired
    private TopicService topicService;
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private AbstractTopicDetailMapper topicMapper;
    @Autowired
    private AbstractPostMapper postMapper;

    @GetMapping("/")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "All topics details.",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = TopicDetailsDto.class))
                    )}
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401",
                    description = "Unauthorized",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponse.class)
                    )}
            )
    })
    public ResponseEntity<?> getAllTopics(Authentication authentication) {
        List<TopicDetailsDto> topicDetailsDtos = new ArrayList<>();
        Iterable<Topic> topicsIterable = this.topicService.getAll();

        User user = this.userService.getUser(authentication.getName());
        if(user == null) {
            return new ResponseEntity<>(new ApiResponse("Error: unknown user."), HttpStatus.UNAUTHORIZED);
        }


        topicsIterable.forEach(it -> {
            TopicDetailsDto topicDetailsDto = topicMapper.toDto(it);
            topicDetailsDto.setSubscribed(it.getSubscribers().contains(user));
            topicDetailsDtos.add(topicDetailsDto);
        });

        return ResponseEntity.ok(topicDetailsDtos);
    }

    @PutMapping("/{id}/subscribe")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Subscribe successfully.",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TopicDetailsDto.class)
                    )}
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400",
                    description = "Bad Request",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponse.class)
                    )}
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401",
                    description = "Unauthorized",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponse.class)
                    )}
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Not Found",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponse.class)
                    )}
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500",
                    description = "Internal Server Error",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponse.class)
                    )}
            )
    })
    public ResponseEntity<?> subscribe(Authentication authentication, @PathVariable("id") final Integer id) {
        User user = this.userService.getUser(authentication.getName());
        if(user == null) {
            return new ResponseEntity<>(new ApiResponse("Error: unknown user."), HttpStatus.UNAUTHORIZED);
        }
        Topic topic = this.topicService.getTopic(id);
        if(topic == null) {
            return new ResponseEntity<>(new ApiResponse("Error: topic not found."), HttpStatus.NOT_FOUND);
        }

        Set<User> subscribers = topic.getSubscribers();
        Set<Topic> subscribes = user.getSubscribes();
        if(subscribers.contains(user) && subscribes.contains(topic)) {
            return new ResponseEntity<>(new ApiResponse("Error: "+user.getUsername()+" already subscribe to "+topic.getTitle()+"."), HttpStatus.BAD_REQUEST);
        } else if(subscribers.contains(user) ^ subscribes.contains(topic)) {
            logger.error("Data consistency: user doesn't have topic in their subscribes or topic doesn't have user in their subscribers but the other has it saved.");
            return new ResponseEntity<>(new ApiResponse("Error: Data consistency."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        subscribers.add(user);
        subscribes.add(topic);

        TopicDetailsDto topicDetailsDto = this.topicMapper.toDto(this.topicService.update(topic));
        topicDetailsDto.setSubscribed(true);
        this.userService.update(user);

        return new ResponseEntity<TopicDetailsDto>(topicDetailsDto, HttpStatus.OK);
    }

    @PutMapping("/{id}/unsubscribe")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Unsubscribe successfully."
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400",
                    description = "Bad Request",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponse.class)
                    )}
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401",
                    description = "Unauthorized",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponse.class)
                    )}
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Not Found",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponse.class)
                    )}
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500",
                    description = "Internal Server Error",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponse.class)
                    )}
            )
    })
    public ResponseEntity<?> unsubscribe(Authentication authentication, @PathVariable("id") final Integer id) {
        User user = this.userService.getUser(authentication.getName());
        if(user == null) {
            return new ResponseEntity<>(new ApiResponse("Error: unknown user."), HttpStatus.UNAUTHORIZED);
        }
        Topic topic = this.topicService.getTopic(id);
        if(topic == null) {
            return new ResponseEntity<>(new ApiResponse("Error: topic not found."), HttpStatus.NOT_FOUND);
        }

        Set<User> subscribers = topic.getSubscribers();
        Set<Topic> subscribes = user.getSubscribes();
        if(!subscribers.contains(user) && !subscribes.contains(topic)) {
            return new ResponseEntity<>(new ApiResponse("Error: "+user.getUsername()+" already unsubscribe to "+topic.getTitle()+"."), HttpStatus.BAD_REQUEST);
        } else if(!subscribers.contains(user) ^ !subscribes.contains(topic)) {
            logger.error("Data consistency: user doesn't have topic in their subscribes or topic doesn't have user in their subscribers but the other has it saved.");
            return new ResponseEntity<>(new ApiResponse("Error: Data consistency."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        subscribers.remove(user);
        subscribes.remove(topic);

        this.topicService.update(topic);
        this.userService.update(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
