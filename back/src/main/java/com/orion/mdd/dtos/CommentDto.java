package com.orion.mdd.dtos;

import lombok.Data;

@Data
public class CommentDto {
    private Integer id;
    private AuthorDto author;
    private String content;
    private String created_at;
}
