package com.orion.mdd.services;

import com.orion.mdd.models.Comment;
import com.orion.mdd.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Iterable<Comment> getAllByPostId(final Integer postId) {
        return this.commentRepository.findAllByPostId(postId);
    }

    public void save(final Comment comment) {
        this.commentRepository.save(comment);
    }
}
