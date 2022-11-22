package com.blogapp.api.services.impl;

import com.blogapp.api.entities.Category;
import com.blogapp.api.entities.Post;
import com.blogapp.api.entities.User;
import com.blogapp.api.exceptions.ResourceNotFoundException;
import com.blogapp.api.payloads.PostDto;
import com.blogapp.api.payloads.PostResponse;
import com.blogapp.api.repositries.CategoryRepo;
import com.blogapp.api.repositries.PostRepo;
import com.blogapp.api.repositries.UserRepositry;
import com.blogapp.api.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepositry userRepositry;
    @Autowired
    private CategoryRepo  categoryRepo;
    @Override
    public PostDto createPost(PostDto postDto,int userId,int catId) {
        User user=this.userRepositry.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
        Category category=categoryRepo.findById(catId).orElseThrow(() ->new ResourceNotFoundException("Category","cat id",catId));

        Post post=this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post updatedPost=this.postRepo.save(post);
        return this.modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, int id) {
        Post post=this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id));
        System.out.println("::::::::::::::: Category Title :::::::::"+ postDto.getCategory().getCategoryTitle());
        Post post1=modelMapper.map(postDto,Post.class);
        post.setCategory(post1.getCategory());
        post.setUser(post1.getUser());
        post.setPostTitle(postDto.getPostTitle());
        post.setDate(postDto.getDate());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost=postRepo.save(post);
        return modelMapper.map(updatedPost,PostDto.class);
    }
    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy) {
//        int pageSize=5;
//        int pageNo=1;
        Pageable p= PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        Page<Post> pagePost=this.postRepo.findAll(p);
        List<Post> posts= pagePost.getContent();
        List<PostDto> postDtos=posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNo(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLasPage(pagePost.isLast());
        return postResponse;
    }
    @Override
    public PostDto getPostById(int id) {
        Post post=this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id));
        return this.modelMapper.map(post,PostDto.class);
    }
    @Override
    public void deletePost(int id) {
        Post post=this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id));
        this.postRepo.delete(post);
    }
    @Override
    public List<PostDto> getPostByCat(int catId) {
        Category cat=this.categoryRepo.findById(catId).orElseThrow(()-> new ResourceNotFoundException("category","category id",catId));
        List<Post> posts=this.postRepo.findByCategory(cat);
        List<PostDto> postDtos=posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(int userId) {
        User user=this.userRepositry.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
        System.out.println("User Name   :::: "+ user.getName());
        List<Post> posts= this.postRepo.findByUser(user);
        for(Post list:posts){
            System.out.println(list.getUser().getName()+list.getContent());

        }
        List<PostDto> postDtos=posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts=this.postRepo.searchPostleContaining("%"+keyword+"%");
        for(Post list:posts){
            System.out.println(list.getUser().getName()+list.getContent()+list.getPostTitle());

        }
        List<PostDto> postList=posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postList;
    }


}
