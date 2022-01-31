package dev.cfan.blogapi.controller;

import dev.cfan.blogapi.domain.Post;
import dev.cfan.blogapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {


    @Autowired
    PostService postService;

    @PostMapping("/new")
    public Post createPost(@RequestBody Post post){
       return postService.createPost(post);
    }

    @GetMapping("/")
    public List<Post> getPosts(){
        return postService.getPosts();
    }
}
