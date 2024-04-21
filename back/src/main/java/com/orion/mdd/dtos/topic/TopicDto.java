package com.orion.mdd.dtos.topic;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.orion.mdd.dtos.post.PostDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
public class TopicDto {
    private Integer id;
    private String title;
    private String description;
    private List<PostDto> posts;
}
