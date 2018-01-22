package com.mhmtyilmaz.controllers;

import com.mhmtyilmaz.config.CustomUserDetails;
import com.mhmtyilmaz.entities.Post;
import com.mhmtyilmaz.services.PostService;
import com.mhmtyilmaz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class BlogController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/posts")
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @PostMapping(value = "/post")
    public String publishPost(@RequestBody Post post){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (post.getDateCreated() == null) post.setDateCreated(new Date());
        post.setCreater(userService.getUser(customUserDetails.getUsername()));
        postService.insert(post);

        return "Post was published";
    }

    @GetMapping(value = "/")
    public String index(){
        return "index";
    }
}
