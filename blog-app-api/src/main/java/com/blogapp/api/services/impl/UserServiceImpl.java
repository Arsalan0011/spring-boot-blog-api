package com.blogapp.api.services.impl;

import com.blogapp.api.entities.Roles;
import com.blogapp.api.entities.User;
import com.blogapp.api.exceptions.ResourceNotFoundException;
import com.blogapp.api.payloads.UserDto;
import com.blogapp.api.repositries.RoleRepositry;
import com.blogapp.api.repositries.UserRepositry;
import com.blogapp.api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepositry userRepositry;
    @Autowired
    @Qualifier("myMapper")
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepositry roleRepositry;

    @Override
    public UserDto registerNewUser(UserDto userDto) {
        User user= this.modelMapper.map(userDto,User.class);
        //encode password
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        // roles
        Roles role= this.roleRepositry.findById(2).get();
        user.getRoles().add(role);
        User newUser=this.userRepositry.save(user);
        return this.modelMapper.map(newUser,UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user=this.dtotoUser(userDto);
        User savedUser=userRepositry.save(user);
        return this.usertoDto(savedUser);}
    @Override
    public UserDto updateUser(UserDto userDto, int id) {
        User user=this.userRepositry.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","id",id));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User upadatedUser=this.userRepositry.save(user);
        return this.usertoDto(upadatedUser);}
    @Override
    public UserDto getUserById(int id) {
        User user=this.userRepositry.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","id",id));
        return this.usertoDto(user);}
    @Override
    public List<UserDto> getAllUsers() {
        List<User> users=this.userRepositry.findAll();
        List<UserDto> userDtos=users.stream().map(user -> this.usertoDto(user)).collect(Collectors.toList());
        return userDtos;}
    @Override
    public void deleteUser(int id) {
        this.userRepositry.deleteById(id);
//        User user=this.userRepositry.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","id",id));
//        this.userRepositry.delete(user);}
    }
    private User dtotoUser(UserDto userDto){
        User user=this.modelMapper.map(userDto,User.class);
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        return user;}
    private UserDto usertoDto(User user){
        UserDto userDto=this.modelMapper.map(user,UserDto.class);
        return userDto;}
}
