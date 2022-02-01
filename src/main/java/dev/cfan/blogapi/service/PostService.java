package dev.cfan.blogapi.service;

import dev.cfan.blogapi.domain.Category;
import dev.cfan.blogapi.domain.Post;
import dev.cfan.blogapi.exception.NotFoundException;
import dev.cfan.blogapi.repository.CategoryRepository;
import dev.cfan.blogapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    final
    PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Autowired
    CategoryRepository categoryRepository;


    public Post createPost(Post post){
        return postRepository.save(post);
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post createCategoryPost(Post post, Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isEmpty()){
            throw new NotFoundException("The Category with ID " + categoryId + " does not exits.  Please create it first. ");
        }

        post.setCategory(category.get());

        return postRepository.save(post);

    }
}
