package com.blogapp.api.services;

import com.blogapp.api.payloads.PostDto;
import com.blogapp.api.payloads.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto  postDto,int userId,int catId);
    PostDto  updatePost(PostDto  postDto,int id);
    PostDto getPostById(int id);
    PostResponse getAllPosts(int pageNo, int pageSize,String sortBy);
    void deletePost(int id);
    List<PostDto> getPostByCat(int catId);
    List<PostDto> getPostByUser(int userId);
    List<PostDto> searchPosts(String keyword);
}
