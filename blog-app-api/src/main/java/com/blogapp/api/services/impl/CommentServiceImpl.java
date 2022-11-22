package com.blogapp.api.services.impl;

import com.blogapp.api.entities.Comment;
import com.blogapp.api.entities.Post;
import com.blogapp.api.exceptions.ResourceNotFoundException;
import com.blogapp.api.payloads.CommentDto;
import com.blogapp.api.repositries.CommentRepo;
import com.blogapp.api.repositries.PostRepo;
import com.blogapp.api.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    @Qualifier("myMapper")
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto commentDto, int postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","id",postId));
        System.out.println("Post title      ::::::::;"+    post.getPostTitle());
        System.out.println("Eroorrrrrrrrrrrrrr"+commentDto.getContent());

        Comment comment=this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment=this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(int commentId) {
        Comment comment=this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Coment","id",commentId));
        this.commentRepo.delete(comment);

    }
}
