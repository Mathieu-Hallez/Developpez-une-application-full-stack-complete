package com.orion.mdd.services;

import com.orion.mdd.models.Comment;
import com.orion.mdd.models.Post;
import com.orion.mdd.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post getPost(final Integer id) {
        return this.postRepository.findById(id).orElse(null);
    }
}
