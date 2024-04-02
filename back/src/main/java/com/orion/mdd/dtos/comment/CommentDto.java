package com.orion.mdd.dtos.comment;

import com.orion.mdd.dtos.user.AuthorDto;
import lombok.Data;

@Data
public class CommentDto {
    private Integer id;
    private AuthorDto author;
    private String content;
    private String created_at;
}
