package com.orion.mdd.mappers;

import com.orion.mdd.dtos.comment.CommentDto;
import com.orion.mdd.models.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@Component
@Mapper(
        componentModel = "spring",
        imports = {
                DateTimeFormatter.class,
                TimeZone.class
        },
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class AbstractCommentMapper implements EntityMapper<CommentDto, Comment> {

    @Override
    @Mappings({
            @Mapping(target = "content", source = "comment"),
            @Mapping(target = "created_at", expression = "java(comment.getCreatedAt().toInstant().atZone(TimeZone.getDefault().toZoneId()).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))")
    })
    public abstract CommentDto toDto(Comment comment);
}
