package com.blogapp.api.repositries;

import com.blogapp.api.entities.Category;
import com.blogapp.api.entities.Post;
import com.blogapp.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
    List<Post> findByCategory(Category category);
    List<Post> findByUser(User user);
    @Query("select p from Post p where p.postTitle like :key")
    List<Post> searchPostleContaining(@Param("key") String title);
}
