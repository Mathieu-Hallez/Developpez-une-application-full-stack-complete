package com.orion.mdd.mappers;

import com.orion.mdd.models.Post;
import com.orion.mdd.dtos.PostDto;
import com.orion.mdd.services.CommentService;
import com.orion.mdd.services.TopicService;
import com.orion.mdd.services.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = {AuthorMapper.class, UserService.class, TopicService.class, CommentService.class}, imports = {})
public abstract class PostMapper implements EntityMapper<PostDto, Post> {

    @Autowired
    UserService userService;
    @Autowired
    TopicService topicService;
    @Autowired
    CommentService commentService;

    @Autowired
    AuthorMapper authorMapper;

    @Override
    @Mappings({
            @Mapping(target = "author", expression = "java(postDto.getAuthor().getId() != null ? this.userService.getUser(postDto.getAuthor().getId()) : null)"),
            @Mapping(target = "topic", expression = "java(postDto.getId() != null ? this.topicService.getByPostId(postDto.getId()) : null)"),
            @Mapping(target = "comments", expression = "java(postDto.getId() != null ? this.commentService.getAllByPostId(postDto.getId()) : null)")
    })
    public abstract Post toEntity(PostDto postDto);

    @Override
    @Mappings({
            @Mapping(target = "created_at", source = "post.createAt", dateFormat = "yyyy/MM/dd")
    })
    public abstract PostDto toDto(Post post);
}
