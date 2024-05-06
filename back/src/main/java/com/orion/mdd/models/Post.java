package com.orion.mdd.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Table(name = "POST")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Post extends AbstractAuditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name="topic_id", nullable = false)
    @JsonBackReference
    private Topic topic;

    @ManyToOne
    @JoinColumn(name="author_id", nullable = false)
    @JsonBackReference
    private User author;

    @OneToMany(mappedBy = "post")
    @JsonManagedReference
    private Set<Comment> comments;
}
