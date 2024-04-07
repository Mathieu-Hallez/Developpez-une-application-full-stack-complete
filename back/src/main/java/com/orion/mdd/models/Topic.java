package com.orion.mdd.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Table(name = "TOPIC")
public class Topic extends AbstractAuditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Integer id;

    @NotNull
    private String title;

    private String description;

    @OneToMany(mappedBy = "topic")
    private Set<Post> posts;
}
