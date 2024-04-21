package com.orion.mdd.dtos.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.orion.mdd.dtos.user.AuthorDto;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PostDto {
    @NotNull
    private Integer id;
    private String title;
    private String content;
    private AuthorDto author;
    private String created_at;
}
