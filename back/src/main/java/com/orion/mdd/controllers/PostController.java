package com.orion.mdd.controllers;

import com.orion.mdd.dtos.api.ApiResponse;
import com.orion.mdd.dtos.comment.CommentDto;
import com.orion.mdd.dtos.comment.CommentPostDto;
import com.orion.mdd.dtos.post.CreatePostDto;
import com.orion.mdd.mappers.AbstractCommentMapper;
import com.orion.mdd.mappers.AbstractPostMapper;
import com.orion.mdd.models.Comment;
import com.orion.mdd.models.Post;
import com.orion.mdd.dtos.post.PostDto;
import com.orion.mdd.models.Topic;
import com.orion.mdd.models.User;
import com.orion.mdd.services.CommentService;
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

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/post")
@Tag(name = "Post", description = "The Post API. Contains all the operations that can be performed on a post.")
public class PostController {

    Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;

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

    @PostMapping("/")
    public ResponseEntity<ApiResponse> createAPost(Authentication authentication, @RequestBody final CreatePostDto createPostDto ) {
        try {
            System.out.println("Authentication name: " + authentication.getName());
            User user = this.userService.getUser(authentication.getName());
            if(user == null) {
                return new ResponseEntity<>(new ApiResponse("Error: Unknown user."), HttpStatus.UNAUTHORIZED);
            }
            Topic topic = this.topicService.getTopic(Integer.valueOf(createPostDto.getTopic_id()));
            if(topic == null) {
                return new ResponseEntity<>(new ApiResponse("Error: Unknown topic."), HttpStatus.NOT_FOUND);
            }

            Post post = Post.builder()
                    .title(createPostDto.getTitle())
                    .content(createPostDto.getContent())
                    .author(user)
                    .topic(topic)
                    .comments(new HashSet<>())
                    .build();

            this.postService.savePost(post);

        } catch (Exception ex) {
            logger.debug(Arrays.toString(ex.getStackTrace()));
            logger.error(ex.getMessage());
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return ResponseEntity.internalServerError().body(new ApiResponse("Error: Internal server error."));
        }
        return ResponseEntity.ok(new ApiResponse("Post successfully created."));
    }

    @PostMapping("/{id}/comment")
    public ResponseEntity<ApiResponse> commentAPost(Authentication authentication, @PathVariable("id") final Integer id, @RequestBody final CommentPostDto commentPostDto) {
        Post post = this.postService.getPost(id);
        if(post == null) {
            return new ResponseEntity<>(new ApiResponse("Error: Post not found."), HttpStatus.NOT_FOUND);
        }

        User user = this.userService.getUser(authentication.getName());
        if(user == null) {
            return new ResponseEntity<>(new ApiResponse("Error: User not found."), HttpStatus.UNAUTHORIZED);
        }

        Comment comment = Comment.builder()
                .comment(commentPostDto.getContent())
                .post(post)
                .author(user)
                .build();

        this.commentService.save(comment);

        return ResponseEntity.ok().build();
    }
}
