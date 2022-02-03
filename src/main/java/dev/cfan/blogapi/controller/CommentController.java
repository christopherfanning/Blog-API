package dev.cfan.blogapi.controller;

import dev.cfan.blogapi.domain.Comment;
import dev.cfan.blogapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/categories/{categoryId}/posts/{postId}/comments")
    public Comment createComment(@PathVariable(value = "categoryId")Long categoryId,
                                 @PathVariable(value = "postId")Long postId,
                                 @RequestBody Comment comment){
        return commentService.createComment(categoryId, postId, comment);
    }
}
