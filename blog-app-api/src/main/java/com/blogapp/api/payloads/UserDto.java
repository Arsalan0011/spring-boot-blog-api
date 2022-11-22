package com.blogapp.api.payloads;

import com.blogapp.api.entities.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int id;
    @NotEmpty(message = "Field must not be empty")
    @Size(min = 3,max = 10,message = "Name must be of length between 3 to 10")
    private String name;
    @Email(message = "Email address is not valid")
    private String email;
    @NotEmpty
    private String password;
    @NotNull
    private String about;

    private Set<RolesDto> roles=new HashSet<>();

    //private List<Post> posts=new ArrayList<>();
}
