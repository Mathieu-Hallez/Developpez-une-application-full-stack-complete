package com.orion.mdd.mappers;

import com.orion.mdd.dtos.CommentDto;
import com.orion.mdd.models.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
@Mapper(
        componentModel = "spring",
        imports = {DateTimeFormatter.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class AbstractCommentMapper implements EntityMapper<CommentDto, Comment> {

    @Override
    @Mappings({
            @Mapping(target = "content", source = "comment"),
            @Mapping(target = "created_at", expression = "java(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(comment.getCreateAt().toLocalDateTime()))")
    })
    public abstract CommentDto toDto(Comment comment);
}
