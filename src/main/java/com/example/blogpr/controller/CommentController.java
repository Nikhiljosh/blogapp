package com.example.blogpr.controller;

import com.example.blogpr.dto.CommentDto;
import com.example.blogpr.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/comments/v1")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("create/{postId}")
    public ResponseEntity<CommentDto> createCommentFromReq(@RequestBody CommentDto commentDto,@PathVariable Integer postId){
        return new ResponseEntity<>(commentService.createComment(commentDto, postId), HttpStatus.CREATED);
    }
}
