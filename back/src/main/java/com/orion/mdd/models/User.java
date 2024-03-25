package com.orion.mdd.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Integer id;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String username;

    @OneToMany(mappedBy = "author")
    private Set<Post> posts;

    @OneToMany(mappedBy = "author")
    private Set<Comment> comments;

    @NotNull
    @Column(name = "create_at")
    private Timestamp createAt;

    @NotNull
    @Column(name = "update_at")
    private LocalDateTime updateAt;
}
