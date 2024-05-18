package com.orion.mdd.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Table(name="COMMENT")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment extends AbstractAuditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String comment;

    @ManyToOne
    @JoinColumn(name="post_id")
    @JsonBackReference
    private Post post;

    @ManyToOne
    @JoinColumn(name="author_id")
    @JsonBackReference
    private User author;
}
