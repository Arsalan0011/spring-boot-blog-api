package com.blogapp.api.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

        private int categoryid;
        @NotBlank
        @Size(min = 3,max = 10,message = "length should be btw 3 to 10")
        private String categoryTitle;
        @NotBlank
        private String categoryDesc;
       // private List<Post> posts=new ArrayList<>();

}
