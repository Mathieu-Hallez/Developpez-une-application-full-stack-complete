package com.orion.mdd.dtos.post;

import lombok.Data;

@Data
public class CreatePostDto {
    private String topic_id;
    private String title;
    private String content;
}
