package com.orion.mdd.mappers;

import com.orion.mdd.models.Post;
import com.orion.mdd.dtos.post.PostDto;
import com.orion.mdd.services.CommentService;
import com.orion.mdd.services.TopicService;
import com.orion.mdd.services.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
                DateTimeFormatter.class,
                TimeZone.class,
                StreamSupport.class,
                Collectors.class,
                Set.class
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

    @Override
    @Mappings({
            @Mapping(target = "author", expression = "java(postDto.getAuthor().getId() != null ? this.userService.getUser(postDto.getAuthor().getId()) : null)"),
            @Mapping(target = "topic", expression = "java(postDto.getId() != null ? this.topicService.getByPostId(postDto.getId()) : null)"),
            @Mapping(
                    target = "comments",
                    expression = "java(postDto.getId() != null ? " +
                    "Set.copyOf(StreamSupport.stream(this.commentService.getAllByPostId(postDto.getId()).spliterator(), false).collect(Collectors.toList()))" +
                    " : null)"
            )
    })
    public abstract Post toEntity(PostDto postDto);

    @Override
    @Mappings({
            @Mapping(target = "created_at", expression = "java(post.getCreatedAt().toInstant().atZone(TimeZone.getDefault().toZoneId()).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))")
    })
    public abstract PostDto toDto(Post post);
}
