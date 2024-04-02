package com.orion.mdd.controllers;

import com.orion.mdd.dtos.CommentDto;
import com.orion.mdd.mappers.AbstractCommentMapper;
import com.orion.mdd.mappers.AbstractPostMapper;
import com.orion.mdd.models.Comment;
import com.orion.mdd.models.Post;
import com.orion.mdd.dtos.PostDto;
import com.orion.mdd.services.CommentService;
import com.orion.mdd.services.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@Tag(name = "Post", description = "The Post API. Contains all the operations that can be performed on a post.")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private AbstractPostMapper postMapper;

    @Autowired
    private AbstractCommentMapper commentMapper;

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable("id") final Integer id) {
        Post post = this.postService.getPost(id);
        if(post == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postMapper.toDto(post));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentDto>> getPostComments(@PathVariable("id") final Integer id) {
        List<Comment> comments = List.copyOf(this.commentService.getAllByPostId(id));

        return ResponseEntity.ok(commentMapper.toDtos(comments));
    }
}
