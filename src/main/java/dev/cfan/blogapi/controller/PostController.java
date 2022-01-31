package dev.cfan.blogapi.controller;

import dev.cfan.blogapi.domain.Post;
import dev.cfan.blogapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {


    @Autowired
    PostService postService;

    @PostMapping("/new")
    public Post createPost(@RequestBody Post post){
       return postService.createPost(post);
    }
}
