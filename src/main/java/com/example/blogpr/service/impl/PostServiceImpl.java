package com.example.blogpr.service.impl;

import com.example.blogpr.dto.PostDto;
import com.example.blogpr.entity.Post;
import com.example.blogpr.repository.PostRepository;
import com.example.blogpr.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {


    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public PostDto createPost(PostDto postDto) {

        Post post = objectMapper.convertValue(postDto, Post.class);
        Post savedPost = postRepository.save(post);
        return objectMapper.convertValue(savedPost, PostDto.class);
    }

    @Override
    public List<PostDto> getAllPosts() {

        return postRepository.findAll()
                .stream()
                .map(post -> objectMapper.convertValue(post, PostDto.class))
                .toList();
    }

    @Override
    public PostDto getPostById(Integer postId) {

        Post post = postRepository.findById(postId).orElseThrow(()-> new RuntimeException("Post not found with id: "+postId));
        return objectMapper.convertValue(post, PostDto.class);
    }
}
