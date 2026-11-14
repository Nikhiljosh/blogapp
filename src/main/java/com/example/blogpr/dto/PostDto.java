package com.example.blogpr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private Integer id;
    private String title;
    private String content;
    private List<CommentDto> commentList;
}
