package com.example.blogpr.service.impl;

import com.example.blogpr.dto.PostDto;
import com.example.blogpr.entity.Post;
import com.example.blogpr.entity.PostImage;
import com.example.blogpr.repository.PostRepository;
import com.example.blogpr.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
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
    public PostDto createPost(PostDto postDto, MultipartFile[] images) throws IOException {

        Post post = new Post();

        post.setPostTags(postDto.getPostTags());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        if(images.length!=0){
            for(MultipartFile image: images){

                PostImage postImage = new PostImage();

                postImage.setImageData(image.getBytes());
                postImage.setImageName(image.getOriginalFilename());
                postImage.setImageType(image.getContentType());

                post.addImage(postImage);

            }
        }
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

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {

        Post post = postRepository.findById(postId).orElseThrow(()-> new RuntimeException("Post not found with id: "+postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setPostTags(postDto.getPostTags());

        return objectMapper.convertValue(postRepository.save(post), PostDto.class);
    }
}
