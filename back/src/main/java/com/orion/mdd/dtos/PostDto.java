package com.orion.mdd.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Data
public class PostDto {
    @NotNull
    private Integer id;
    private String title;
    private String content;
    private AuthorDto author;
    private String created_at;
}
