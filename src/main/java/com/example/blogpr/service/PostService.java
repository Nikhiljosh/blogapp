package com.example.blogpr.service;

import com.example.blogpr.dto.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPosts();
    PostDto getPostById(Integer postId);
    PostDto updatePost(PostDto postDto, Integer postId);
}
