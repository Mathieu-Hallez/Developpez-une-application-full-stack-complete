package com.orion.mdd.controllers;

import com.orion.mdd.dtos.api.ApiResponse;
import com.orion.mdd.dtos.topic.TopicDetailsDto;
import com.orion.mdd.dtos.topic.TopicDto;
import com.orion.mdd.dtos.user.UpdateUserDto;
import com.orion.mdd.mappers.AbstractTopicDetailMapper;
import com.orion.mdd.mappers.AbstractTopicMapper;
import com.orion.mdd.models.Topic;
import com.orion.mdd.models.User;
import com.orion.mdd.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users/")
@Tag(name = "Users", description = "The Users API. Contains all the operations that can be performed on users.")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AbstractTopicDetailMapper topicDetailMapper;
    @Autowired
    private AbstractTopicMapper topicMapper;

    @GetMapping("/subscriptions")
    public ResponseEntity<?> getAllSubscriptions(Authentication authentication) {
        List<TopicDetailsDto> topicDetailsDtos = new ArrayList<>();

        User user = this.userService.getUser(authentication.getName());
        if(user == null) {
            return new ResponseEntity<>(new ApiResponse("Error: user not found."), HttpStatus.UNAUTHORIZED);
        }

        user.getSubscribes().forEach(it -> topicDetailsDtos.add(this.topicDetailMapper.toDto(it)));

        return ResponseEntity.ok(topicDetailsDtos);
    }

    @PutMapping("/")
    public ResponseEntity<?> update(Authentication authentication, @RequestBody(required=false) UpdateUserDto updateUserDto) {
        User user = this.userService.getUser(authentication.getName());
        if(user == null) {
            return new ResponseEntity<>(new ApiResponse("Error: user not found."), HttpStatus.UNAUTHORIZED);
        }

        if(updateUserDto.getUsername() != null) {
            user.setUsername(updateUserDto.getUsername());
        }

        if(updateUserDto.getEmail() != null) {
            user.setEmail(updateUserDto.getEmail());
        }

        this.userService.update(user);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/subscriptions/posts")
    public ResponseEntity<?> getAllPostsBySubscriptions(Authentication authentication) {
        User user = this.userService.getUser(authentication.getName());
        if(user == null) {
            return new ResponseEntity<>(new ApiResponse("Error: user not found."), HttpStatus.UNAUTHORIZED);
        }

        List<TopicDto> topicDtos = new ArrayList<>();

        user.getSubscribes().forEach(it -> topicDtos.add((TopicDto) topicMapper.toDto(it)));

        return new ResponseEntity<>(topicDtos, HttpStatus.OK);
    }
}