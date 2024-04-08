package com.orion.mdd.controllers;

import com.orion.mdd.dtos.api.ApiResponse;
import com.orion.mdd.dtos.post.PostDto;
import com.orion.mdd.dtos.topic.TopicDto;
import com.orion.mdd.mappers.AbstractPostMapper;
import com.orion.mdd.mappers.AbstractTopicMapper;
import com.orion.mdd.models.Post;
import com.orion.mdd.models.Topic;
import com.orion.mdd.models.User;
import com.orion.mdd.services.PostService;
import com.orion.mdd.services.TopicService;
import com.orion.mdd.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private AbstractTopicMapper topicMapper;
    @Autowired
    private AbstractPostMapper postMapper;

    @GetMapping("/")
    public ResponseEntity<List<TopicDto>> getAllTopics() {
        List<TopicDto> topicDtos = new ArrayList<>();
        Iterable<Topic> topicsIterable = this.topicService.getAll();

        topicsIterable.forEach(it -> topicDtos.add(topicMapper.toDto(it)));

        return ResponseEntity.ok(topicDtos);
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<List<PostDto>> getAllPostsTopic(@PathVariable("id") final Integer id) {
        List<PostDto> postDtos = new ArrayList<>();
        Iterable<Post> postIterable = this.postService.getAllPostsOfATopic(id);
        
        postIterable.forEach(it -> postDtos.add(this.postMapper.toDto(it)));
        
        return ResponseEntity.ok(postDtos);
    }

    @PutMapping("/{id}/subscribe")
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

        this.topicService.update(topic);
        this.userService.update(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/unsubscribe")
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
