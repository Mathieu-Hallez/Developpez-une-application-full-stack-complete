package com.orion.mdd.mappers;

import com.orion.mdd.models.Post;
import com.orion.mdd.dtos.PostDto;
import com.orion.mdd.services.CommentService;
import com.orion.mdd.services.TopicService;
import com.orion.mdd.services.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;

@Component
@Mapper(
        componentModel = "spring",
        uses = {
                AbstractAuthorMapper.class,
                UserService.class,
                TopicService.class,
                CommentService.class
        },
        imports = {
                Timestamp.class,
                Instant.class
        },
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class AbstractPostMapper implements EntityMapper<PostDto, Post> {

    @Autowired
    UserService userService;
    @Autowired
    TopicService topicService;
    @Autowired
    CommentService commentService;

    @Autowired
    AbstractAuthorMapper authorMapper;

    @Override
    @Mappings({
            @Mapping(target = "author", expression = "java(postDto.getAuthor().getId() != null ? this.userService.getUser(postDto.getAuthor().getId()) : null)"),
            @Mapping(target = "topic", expression = "java(postDto.getId() != null ? this.topicService.getByPostId(postDto.getId()) : null)"),
            @Mapping(target = "comments", expression = "java(postDto.getId() != null ? this.commentService.getAllByPostId(postDto.getId()) : null)"),
            @Mapping(target = "createAt", expression = "java(Timestamp.from(Instant.parse(postDto.getCreated_at())))")
    })
    public abstract Post toEntity(PostDto postDto);

    @Override
    @Mappings({
            @Mapping(target = "created_at", source = "post.createAt", dateFormat = "yyyy/MM/dd")
    })
    public abstract PostDto toDto(Post post);
}