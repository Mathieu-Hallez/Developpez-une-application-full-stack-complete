package com.orion.mdd.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "TOPIC")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Integer id;

    @NotNull
    private String title;

    private String description;

    @OneToMany(mappedBy = "topic")
    private Set<Post> posts;

    @NotNull
    @Column(name = "create_at")
    private Timestamp createAt;

    @NotNull
    @Column(name = "update_at")
    private LocalDateTime updateAt;
}
