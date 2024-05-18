package com.orion.mdd.dtos.post;

import com.orion.mdd.dtos.topic.TopicDetailsDto;
import com.orion.mdd.dtos.user.AuthorDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PostDto {
    @NotNull
    private Integer id;
    private String title;
    private String content;
    private AuthorDto author;
    private TopicDetailsDto topic;
    private String created_at;
}
