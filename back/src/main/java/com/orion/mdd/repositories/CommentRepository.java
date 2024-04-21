package com.orion.mdd.repositories;

import com.orion.mdd.models.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
    Iterable<Comment> findAllByPostId(final Integer id);
}
