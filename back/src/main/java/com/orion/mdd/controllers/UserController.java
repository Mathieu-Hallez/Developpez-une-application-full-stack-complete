package com.orion.mdd.controllers;

import com.orion.mdd.configurations.SpringSecurityConfig;
import com.orion.mdd.dtos.api.ApiResponse;
import com.orion.mdd.dtos.post.PostDto;
import com.orion.mdd.dtos.topic.TopicDetailsDto;
import com.orion.mdd.dtos.user.UpdateUserDto;
import com.orion.mdd.dtos.user.UpdateUserRequestDto;
import com.orion.mdd.mappers.AbstractPostMapper;
import com.orion.mdd.mappers.AbstractTopicDetailMapper;
import com.orion.mdd.mappers.AbstractUserMapper;
import com.orion.mdd.models.User;
import com.orion.mdd.services.UserService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users/")
@Tag(name = "Users", description = "The Users API. Contains all the operations that can be performed on users.")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AbstractTopicDetailMapper topicDetailMapper;
    @Autowired
    private AbstractPostMapper postMapper;

    @Autowired
    private AbstractUserMapper userMapper;

    @Autowired
    private SpringSecurityConfig springSecurityConfig;

    @GetMapping("/me")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "User information.",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UpdateUserDto.class)
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
    public ResponseEntity<?> getMe(Authentication authentication) {
        User user = this.userService.getUser(authentication.getName());
        if(user == null) {
            return new ResponseEntity<>(new ApiResponse("Error: user not found."), HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<UpdateUserDto>(this.userMapper.toDto(this.userService.update(user)), HttpStatus.OK);
    }

    @GetMapping("/subscriptions")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "All subscribe topics.",
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
    public ResponseEntity<?> getAllSubscriptions(Authentication authentication) {
        List<TopicDetailsDto> topicDetailsDtos = new ArrayList<>();

        User user = this.userService.getUser(authentication.getName());
        if(user == null) {
            return new ResponseEntity<>(new ApiResponse("Error: user not found."), HttpStatus.UNAUTHORIZED);
        }

        user.getSubscribes().forEach(it -> {
            TopicDetailsDto topicDetailsDto = this.topicDetailMapper.toDto(it);
            topicDetailsDto.setSubscribed(true);
            topicDetailsDtos.add(topicDetailsDto);
        });

        return ResponseEntity.ok(topicDetailsDtos);
    }

    @PutMapping("/")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "User updated.",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UpdateUserDto.class)
                    )}
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401",
                    description = "Unauthorized",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponse.class)
                    )}
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400",
                    description = "BadRequest",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponse.class)
                    )}
            )
    })
    public ResponseEntity<?> update(Authentication authentication, @Valid @RequestBody(required=false) UpdateUserRequestDto updateUserDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(new ApiResponse("Error: " + String.join("\n", errorMessages)));
        }

        User user = this.userService.getUser(authentication.getName());
        if(user == null) {
            return new ResponseEntity<ApiResponse>(new ApiResponse("Error: user not found."), HttpStatus.UNAUTHORIZED);
        }

        if(updateUserDto.getUsername() != null) {
            user.setUsername(updateUserDto.getUsername());
        }

        if(updateUserDto.getEmail() != null) {
            user.setEmail(updateUserDto.getEmail());
        }

        if(updateUserDto.getPassword() != null) {
            user.setPassword(this.springSecurityConfig.passwordEncoder().encode(updateUserDto.getPassword()));
        }

        return new ResponseEntity<UpdateUserDto>(this.userMapper.toDto(this.userService.update(user)), HttpStatus.OK);
    }

    @GetMapping("/subscriptions/posts")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "All posts of subscriptions user.",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = PostDto.class))
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
    public ResponseEntity<?> getAllPostsOfSubscriptions(Authentication authentication) {
        User user = this.userService.getUser(authentication.getName());
        if(user == null) {
            return new ResponseEntity<>(new ApiResponse("Error: user not found."), HttpStatus.UNAUTHORIZED);
        }

        List<PostDto> postDtos = new ArrayList<>();

        user.getSubscribes().forEach(topic -> topic.getPosts().forEach(post -> postDtos.add(postMapper.toDto(post))));

        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }
}
