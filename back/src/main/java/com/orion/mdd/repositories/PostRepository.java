package com.orion.mdd.repositories;

import com.orion.mdd.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {
    Iterable<Post> findAllByTopicId(final Integer id);
}
