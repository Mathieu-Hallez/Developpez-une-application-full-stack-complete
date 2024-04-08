package com.orion.mdd.controllers;

import com.orion.mdd.dtos.post.PostDto;
import com.orion.mdd.dtos.topic.TopicDto;
import com.orion.mdd.mappers.AbstractPostMapper;
import com.orion.mdd.mappers.AbstractTopicMapper;
import com.orion.mdd.models.Post;
import com.orion.mdd.models.Topic;
import com.orion.mdd.services.PostService;
import com.orion.mdd.services.TopicService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/topic/")
@Tag(name = "Topic", description = "The Topic API. Contains all the operations that can be performed on a topic.")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private PostService postService;

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
}
