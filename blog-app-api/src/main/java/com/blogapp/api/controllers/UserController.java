package com.blogapp.api.controllers;

import com.blogapp.api.payloads.ApiResponse;
import com.blogapp.api.payloads.UserDto;
import com.blogapp.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/api/users")   //add new user
    public ResponseEntity<UserDto> createUser(@Valid   @RequestBody UserDto userDto){
        UserDto createUserDto=this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }
    @PutMapping("/api/users/{userId}")  //update user
    public ResponseEntity<String> updateUser(@Valid @PathVariable int userId, @RequestBody UserDto userDto){
        this.userService.updateUser(userDto,userId);
        return ResponseEntity.ok ("user updated");
    }
    @DeleteMapping("/api/users/{userId}")   //delete user
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable int userId){
        this.userService.deleteUser(userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfrully",true),HttpStatus.OK);
    }
    @GetMapping("/api/users")   //get all users
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }
    @GetMapping("/api/users/{id}")   //get user by id
    public ResponseEntity<UserDto> getUserById(@PathVariable int id){
        return ResponseEntity.ok(this.userService.getUserById(id));
    }
}

