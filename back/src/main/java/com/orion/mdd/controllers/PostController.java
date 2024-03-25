package com.orion.mdd.controllers;

import com.orion.mdd.mappers.PostMapper;
import com.orion.mdd.models.Post;
import com.orion.mdd.dtos.PostDto;
import com.orion.mdd.services.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
@Tag(name = "Post", description = "The Post API. Contains all the operations that can be performed on a post.")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostMapper postMapper;

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable("id") final Integer id) {
        Post post = this.postService.getPost(id);
        if(post == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(postMapper.toDto(post), HttpStatus.OK);
    }
}
