package dev.cfan.blogapi.service;

import dev.cfan.blogapi.domain.Category;
import dev.cfan.blogapi.domain.Comment;
import dev.cfan.blogapi.domain.Post;
import dev.cfan.blogapi.exception.NotFoundException;
import dev.cfan.blogapi.repository.CategoryRepository;
import dev.cfan.blogapi.repository.CommentRepository;
import dev.cfan.blogapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    public Comment createComment(Long categoryId, Long postId, Comment comment) {

        Optional<Category> category = categoryRepository.findById(categoryId);
if (category.isEmpty()){
    throw new NotFoundException("Category not found, cannot post a comment.");
}
        Optional<Post> post = postRepository.findById(postId);
if(post.isEmpty()){
    throw new NotFoundException("There is no post here to comment on...");
}

        comment.setPost(post.get());

        commentRepository.save(comment);

        return comment;
    }
}
