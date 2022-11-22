package com.blogapp.api.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private int postId;
    private String postTitle;
    private String content;
    private String imageName;
    private Date date;
    private CategoryDto category;
    private UserDto user;
    private List<CommentDto> comments=new ArrayList<>();
}
