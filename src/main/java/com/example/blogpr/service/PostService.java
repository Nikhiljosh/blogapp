package com.example.blogpr.service;

import com.example.blogpr.dto.PostDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, MultipartFile[] images) throws IOException;
    List<PostDto> getAllPosts();
    PostDto getPostById(Integer postId);
    PostDto updatePost(PostDto postDto, Integer postId);
}
