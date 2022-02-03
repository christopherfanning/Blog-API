package dev.cfan.blogapi.controller;

import dev.cfan.blogapi.domain.Post;
import dev.cfan.blogapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {


    @Autowired
    PostService postService;

    @PostMapping("/new/posts/")
    public Post createPost(@RequestBody Post post){
        // Uncategorized post.
       return postService.createPost(post);
    }

    @GetMapping("/posts")
    public List<Post> getPosts(){
        //get all the posts
        return postService.getPosts();
    }

    @PostMapping("/categories/{categoryId}/posts")
    public Post createCategoryPost(@RequestBody Post post, @PathVariable(value = "categoryId") Long categoryId){
        return postService.createCategoryPost(post, categoryId);
    }

    @DeleteMapping("/categories/{categoryId}/posts/{postId}")
    public Post deleteCategoryPost(
            @PathVariable(value = "categoryId") Long categoryId,
            @PathVariable(value = "postId") Long postId){
        return postService.deleteCategoryPost(categoryId, postId);
    }

    @PutMapping("/categories/{categoryId}/posts/{postId}")
    public Post updateCategoryPost(
            @PathVariable(value = "categoryId") Long categoryId,
            @PathVariable(value = "postId") Long postId,
            @RequestBody Post post){
        System.out.println("Put mapping taking place!");
        return postService.updateCategoryPost(categoryId, postId, post);
    }
}
