package com.example.demo.services;

import com.example.demo.models.Blog;
import com.example.demo.repositories.BlogRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    // 添加博客到数据库
    @Transactional
    public void addBlogToDatabase(String title, String content) {
        Blog blog = new Blog(title, content);
        blogRepository.save(blog);
        blogRepository.flush();
    }


    // 删除博客
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    // 编辑博客
    public void editBlog(Long id, String newContent) {
        Blog blog = blogRepository.findById(id).orElse(null);
        if (blog != null) {
            blog.setContent(newContent);
            blogRepository.save(blog);
        }
    }

    // 获取所有博客
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    // 根据ID获取博客
    public Blog getBlogById(Long id) {
        return blogRepository.findById(id).orElse(null);
    }

    // 根据关键词搜索博客
    public List<Blog> searchBlogs(String keyword) {
        return blogRepository.findByContentContaining(keyword);
    }
}
