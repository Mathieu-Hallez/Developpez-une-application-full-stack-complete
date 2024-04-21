package com.orion.mdd.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
    @JsonManagedReference
    private Set<Post> posts;

    @ManyToMany(mappedBy = "subscribes")
    @JsonIgnore
    private Set<User> subscribers;
}
