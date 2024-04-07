package com.orion.mdd.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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
    private Post post;

    @ManyToOne
    @JoinColumn(name="author_id")
    private User author;
}
