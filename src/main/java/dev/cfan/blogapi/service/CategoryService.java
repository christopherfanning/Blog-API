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
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    PostRepository postRepository;

    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public List<Post> getCategoryPosts(Long categoryId) {
        return postRepository.findByCategoryId(categoryId);
    }

    public Category deleteCategory(Long categoryId) {
        System.out.println("Calling delete from categoryService");
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()){
            throw new NotFoundException("The category with id " + categoryId + " is not found. Cannot delete.");
        }
        categoryRepository.deleteById(categoryId);
        return category.get();
    }
}
