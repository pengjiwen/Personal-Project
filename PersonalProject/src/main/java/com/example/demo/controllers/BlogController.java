package com.example.demo.controllers;

import com.example.demo.services.BlogService;
import com.example.demo.models.Blog;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    // 博客页面
    @GetMapping("/blog")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("user", username);
        return "blog";
    }

    // 添加博客
    @PostMapping("/add-blog")
    public String addBlog(@RequestParam String title, @RequestParam String content) {
        blogService.addBlogToDatabase(title, content);
        return "redirect:/blog";
    }

    // 删除博客
    @PostMapping("/delete-blog")
    public String deleteBlog(@RequestParam Long blogId) {
        blogService.deleteBlog(blogId);
        return "redirect:/blog";
    }

    // 编辑博客
    @PostMapping("/edit-blog")
    public String editBlog(@RequestParam Long blogId, @RequestParam String newContent) {
        blogService.editBlog(blogId, newContent);
        return "redirect:/blog";
    }

    // 搜索博客
    @GetMapping("/search")
    public ModelAndView searchBlog(@RequestParam String keyword, ModelAndView mav) {
        List<Blog> blogs = blogService.searchBlogs(keyword);
        mav.addObject("blogs", blogs);
        mav.setViewName("search");
        return mav;
    }
}
