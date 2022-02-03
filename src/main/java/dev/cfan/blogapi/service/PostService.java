package dev.cfan.blogapi.service;

import dev.cfan.blogapi.domain.Category;
import dev.cfan.blogapi.domain.JpaUser;
import dev.cfan.blogapi.domain.Post;
import dev.cfan.blogapi.exception.InformationExistException;
import dev.cfan.blogapi.exception.NotFoundException;
import dev.cfan.blogapi.repository.CategoryRepository;
import dev.cfan.blogapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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


    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post createCategoryPost(Post postObject, Long categoryId) {
        JpaUser userDetails = (JpaUser) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Optional<Category> category = categoryRepository.findById(categoryId);


        if (category.isEmpty()) {
            throw new NotFoundException(
                    "category with id " + categoryId + " not belongs to this user or category does not exist");
        }
        Post post = postRepository.findByTitleAndUserId(
                postObject.getTitle(),
                userDetails.getUser().getId());
        if (post != null) {
            throw new InformationExistException("recipe with name " + post.getTitle() + " already exists");
        }
        postObject.setUser(userDetails.getUser());
        postObject.setCategory(category.get());
        postObject.setAuthor(userDetails.getUsername());

        return postRepository.save(postObject);

    }

    public Post deleteCategoryPost(Long categoryId, Long postId) {
        // Check for categoryId
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) {
            throw new NotFoundException("Category with id " + categoryId + "not found, therefor there cannot be a post associated with it. Delete Failed.");
        }
        // Check for postId
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) {
            throw new NotFoundException("There is no post with id " + postId + " in category with id " + categoryId + ". DeletePost failed.");
        }
        // delete that post
        postRepository.deleteById(postId);
        // return that deleted post
        return post.get();
        // TODO Update to only allow deletions if the user created it.
    }

    public Post updateCategoryPost(Long categoryId, Long postId, Post post) {
        // get the category
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) {
            throw new NotFoundException("This category with id: " + categoryId + " doesn't exist. Can't update it's post. Update failed.");
        }
        // get the old post
        Optional<Post> oldPost = postRepository.findById(postId);
        if (oldPost.isEmpty()) {
            throw new NotFoundException("The post with id: " + postId + " does not exist. Update Failed.");
        }
        // update the contents of the old post with the new stuff.
        oldPost.get().setTitle(post.getTitle());
        oldPost.get().setContent(post.getContent());
        return postRepository.save(oldPost.get());


    }

    public Post getCategoryPost(Long categoryId, Long postId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) {
            throw new NotFoundException("This category with id: " + categoryId + " doesn't exist. Can't update it's post. Update failed.");
        }
        // get the old post
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) {
            throw new NotFoundException("The post with id: " + postId + " does not exist. Update Failed.");
        }

        return post.get();

    }
}
