package com.orion.mdd.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "POST")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Integer id;

    @NotNull
    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name="topic_id", nullable = false)
    private Topic topic;

    @ManyToOne
    @JoinColumn(name="author_id", nullable = false)
    private User author;

    @OneToMany(mappedBy = "post")
    private Set<Comment> comments;

    @NotNull
    @Column(name = "create_at")
    private Timestamp createAt;

    @NotNull
    @Column(name = "update_at")
    private LocalDateTime updateAt;
}
