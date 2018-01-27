package com.mhmtyilmaz.controllers;

import com.mhmtyilmaz.config.CustomUserDetails;
import com.mhmtyilmaz.entities.Post;
import com.mhmtyilmaz.services.PostService;
import com.mhmtyilmaz.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class BlogController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @PostMapping
    public String publishPost(@RequestBody Post post){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (post.getDateCreated() == null) post.setDateCreated(new Date());
        post.setCreator(userService.getUser(customUserDetails.getUsername()));
        postService.insert(post);

        return "Post was published";
    }

    @GetMapping(value = "/{username}")
    public List<Post> postsByUserName(@PathVariable String username){
        return postService.findByUser(userService.getUser(username));
    }
}
