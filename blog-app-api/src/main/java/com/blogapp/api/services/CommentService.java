package com.blogapp.api.services;

import com.blogapp.api.payloads.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto,int postId);
    void deleteComment(int commentId);

}
