package com.example.blogpr.service;

import com.example.blogpr.dto.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Integer postId);
}
