package com.example.blogpr.service.impl;

import com.example.blogpr.dto.CommentDto;
import com.example.blogpr.entity.Comment;
import com.example.blogpr.entity.Post;
import com.example.blogpr.repository.CommentRepository;
import com.example.blogpr.repository.PostRepository;
import com.example.blogpr.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {

        Post post = postRepository.findById(postId).orElseThrow(()-> new RuntimeException("Post not found with id: "+postId));

        Comment comment = objectMapper.convertValue(commentDto, Comment.class);

        comment.setPost(post);
        comment.setComment(commentDto.getComment());
        commentRepository.save(comment);

        return objectMapper.convertValue(comment, CommentDto.class);
    }
}
