package com.orion.mdd.services;

import com.orion.mdd.models.Post;
import com.orion.mdd.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post getPost(final Integer id) {
        return this.postRepository.findById(id).orElse(null);
    }

    public void savePost(final Post post) {
        this.postRepository.save(post);
    }
}
