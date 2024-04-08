package com.orion.mdd.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.util.Set;

@Entity
@Data
@Table(name = "USER")
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User extends AbstractAuditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @ToStringExclude
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

    @ManyToMany
    @JoinTable(
            name = "subscribe",
            joinColumns = @JoinColumn(name = "subscriber_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private Set<Topic> subscribes;
}
