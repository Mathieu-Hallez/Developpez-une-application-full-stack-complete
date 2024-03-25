package com.orion.mdd.repositories;

import com.orion.mdd.models.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends CrudRepository<Topic, Integer> {
    Optional<Topic> findByPostsId(final Integer id);
}
