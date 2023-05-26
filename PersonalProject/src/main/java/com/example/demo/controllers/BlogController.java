package com.example.demo.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.services.AccountService;
import com.example.demo.services.BlogService;
import com.example.demo.models.Blog;

@Controller
public class BlogController {
    private BlogService blogService;
    private AccountService accountService;

    public BlogController(BlogService blogService, AccountService accountService) {
        this.blogService = blogService;
        this.accountService = accountService;
    }

    // 博客页面
    @GetMapping("/blog")
    public String index() {
        return "blog";
    }

    // 登录页面
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // 登录验证
    @PostMapping("/login")
    public ModelAndView processLogin(@RequestParam String username, @RequestParam String password, ModelAndView mav) {
        if (accountService.newAccount(username, password)) {
            mav.addObject("user", username);
            mav.setViewName("redirect:/blog");
        } else {
            mav.addObject("error", "error");
            mav.setViewName("login");
        }
        return mav;
    }
    
    // 添加博客
    @PostMapping("/add-blog")
    public String addBlog(@RequestParam String title, @RequestParam String content) {
        blogService.addBlog(title, content);
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
        mav.setViewName("blog");
        return mav;
    }
}
