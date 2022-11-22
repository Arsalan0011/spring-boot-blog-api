package com.blogapp.api;

import com.blogapp.api.entities.Roles;
import com.blogapp.api.repositries.RoleRepositry;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BlogAppApiApplication implements CommandLineRunner  {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepositry roleRepositry;

    public static void main(String[] args) {
        SpringApplication.run(BlogAppApiApplication.class, args);
    }
    @Bean(name = "myMapper")
    public ModelMapper modelMapper(){
        return new ModelMapper();

    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(this.passwordEncoder.encode("123"));
        try{
            Roles role=new Roles();
            role.setId(2);
            role.setName("User");

            Roles role1=new Roles();
            role1.setId(1);
            role1.setName("Admin");

            List<Roles> roles=new ArrayList<>();
            roles.add(role);
            roles.add(role1);
            List<Roles> result=roleRepositry.saveAll(roles);

            result.forEach(r->{
                System.out.println(r.getName());
            });
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
}
