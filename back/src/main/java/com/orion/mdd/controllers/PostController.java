package com.orion.mdd.controllers;

import com.orion.mdd.dtos.api.ApiResponse;
import com.orion.mdd.dtos.comment.CommentDto;
import com.orion.mdd.dtos.comment.CommentPostDto;
import com.orion.mdd.dtos.post.CreatePostDto;
import com.orion.mdd.dtos.post.PostDto;
import com.orion.mdd.dtos.topic.TopicDetailsDto;
import com.orion.mdd.mappers.AbstractCommentMapper;
import com.orion.mdd.mappers.AbstractPostMapper;
import com.orion.mdd.models.Comment;
import com.orion.mdd.models.Post;
import com.orion.mdd.models.Topic;
import com.orion.mdd.models.User;
import com.orion.mdd.services.CommentService;
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
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@Tag(name = "Posts", description = "The Posts API. Contains all the operations that can be performed on posts.")
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
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Get a post detail.",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PostDto.class)
                    )}
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Not Found",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponse.class)
                    )}
            )
    })
    public ResponseEntity<PostDto> getPost(@PathVariable("id") final Integer id) {
        Post post = this.postService.getPost(id);
        if(post == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postMapper.toDto(post));
    }

    @GetMapping("/{id}/comments")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "All post comments.",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = CommentDto.class))
                    )}
            )
    })
    public ResponseEntity<List<CommentDto>> getPostComments(@PathVariable("id") final Integer id) {
        List<CommentDto> commentDtos = new ArrayList<>();
        Iterable<Comment> commentIterable = this.commentService.getAllByPostId(id);

        commentIterable.forEach(it -> commentDtos.add(commentMapper.toDto(it)));

        return ResponseEntity.ok(commentDtos);
    }

    @PostMapping("/create")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Post create successfully.",
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
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Comment post successfully.",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TopicDetailsDto.class)
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
            )
    })
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
