package com.orion.mdd.services;

import com.orion.mdd.models.Topic;
import com.orion.mdd.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    public Topic getByPostId(final Integer postId) {
        return this.topicRepository.findByPostsId(postId).orElse(null);
    }
}
