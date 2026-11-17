package com.example.blogpr.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList;

    @OneToMany(mappedBy = "post")
    private List<PostImage> images = new ArrayList<>();

    public void addImage(PostImage postImage){
        this.images.add(postImage);
        postImage.setPost(this);
    }

    @ElementCollection
    @CollectionTable(name = "posttags",
    joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"))
    @Column(name="tag")
    private Set<String> postTags;
}
