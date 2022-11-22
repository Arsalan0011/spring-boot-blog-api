package com.blogapp.api.services;

import com.blogapp.api.payloads.UserDto;

import java.util.List;

public interface UserService {
   UserDto registerNewUser(UserDto userDto);
   UserDto createUser(UserDto userDto);
   UserDto updateUser(UserDto userDto,int id);
   UserDto getUserById(int id);
   List<UserDto> getAllUsers();
   void deleteUser(int id);
}
