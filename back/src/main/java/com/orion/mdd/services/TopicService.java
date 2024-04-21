package com.orion.mdd.services;

import com.orion.mdd.models.Topic;
import com.orion.mdd.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    public Topic getTopic(final Integer id) {
        return this.topicRepository.findById(id).orElse(null);
    }

    public Topic getByPostId(final Integer postId) {
        return this.topicRepository.findByPostsId(postId).orElse(null);
    }

    public Iterable<Topic> getAll() {
        return this.topicRepository.findAll();
    }

    public void update(Topic topic) {
        this.topicRepository.save(topic);
    }
}
