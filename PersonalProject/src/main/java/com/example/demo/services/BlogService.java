package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.models.Blog;

@Service
public class BlogService {
    private List<Blog> blogs;

    public BlogService() {
        this.blogs = new ArrayList<>();
    }

    // 添加博客
    public void addBlog(String title, String content) {
        Blog blog = new Blog(title, content);
        blogs.add(blog);
    }

    // 删除博客
    public void deleteBlog(Long id) {
        blogs.removeIf(blog -> blog.getId().equals(id));
    }

    // 编辑博客
    public void editBlog(Long id, String newContent) {
        for (Blog blog : blogs) {
            if (blog.getId().equals(id)) {
                blog.setContent(newContent);
                break;
            }
        }
    }

    // 获取所有博客
    public List<Blog> getAllBlogs() {
        return blogs;
    }

    // 根据ID获取博客
    public Blog getBlogById(Long id) {
        for (Blog blog : blogs) {
            if (blog.getId().equals(id)) {
                return blog;
            }
        }
        return null;
    }

    // 根据关键词搜索博客
    public List<Blog> searchBlogs(String keyword) {
        List<Blog> matchedBlogs = new ArrayList<>();
        for (Blog blog : blogs) {
            if (blog.getContent().contains(keyword)) {
                matchedBlogs.add(blog);
            }
        }
        return matchedBlogs;
    }
}
