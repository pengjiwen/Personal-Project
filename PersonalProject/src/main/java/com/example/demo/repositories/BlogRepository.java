package com.example.demo.repositories;

import com.example.demo.models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findByContentContaining(String keyword);
}
