package com.blogapp.api.controllers;

import com.blogapp.api.payloads.ApiResponse;
import com.blogapp.api.payloads.PostDto;
import com.blogapp.api.payloads.PostResponse;
import com.blogapp.api.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping("/api/user/{userId}/category/{catId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable int userId, @PathVariable int catId){
        PostDto createdPost= this.postService.createPost(postDto, userId, catId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }
    @GetMapping("/api/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable int userId){
        List<PostDto> posts= this.postService.getPostByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
     }
    @GetMapping("/api/category/{catId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable int catId){
        List<PostDto> posts= this.postService.getPostByCat(catId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    @DeleteMapping("/api/users/posts/{id}")   //delete post
    public ResponseEntity<ApiResponse> deletePost(@PathVariable int id){
        this.postService.deletePost(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfrully",true),HttpStatus.OK);
    }
    @GetMapping("/api/users/posts")   //get all posts
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam (value = "pageNo",defaultValue = "0",required = false)
            int pageNo, @RequestParam(value = "pageSize",defaultValue = "5",required = false)
            int pageSize, @RequestParam(value = "sortBy",defaultValue = "postId",required = false) String sortBy){
            PostResponse postResponse=this.postService.getAllPosts(pageNo,pageSize,sortBy);
            return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
    }
    @GetMapping("/api/users/posts/{id}")   //get post by id
    public ResponseEntity<PostDto> getPostById(@PathVariable int id){
        return ResponseEntity.ok(this.postService.getPostById(id));
    }
    @PutMapping("/api/users/posts/{id}")   //update post by id
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto ,@PathVariable int id){
        PostDto updatePost=this.postService.updatePost(postDto,id);
        return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
    }
    @GetMapping("/api/users/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keywords){
        List<PostDto> postList=this.postService.searchPosts(keywords);
        return new ResponseEntity<List<PostDto>>(postList,HttpStatus.OK);
    }             //search
}
