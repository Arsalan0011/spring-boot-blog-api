package com.blogapp.api.security;

import com.blogapp.api.entities.User;
import com.blogapp.api.exceptions.ResourceNotFoundException;
import com.blogapp.api.repositries.UserRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepositry userRepositry;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Loading user from database
        User user=this.userRepositry.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User","email"+username,0));
        return user;
    }
}
