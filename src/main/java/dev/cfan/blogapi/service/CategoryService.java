package dev.cfan.blogapi.service;

import dev.cfan.blogapi.domain.Category;
import dev.cfan.blogapi.domain.Post;
import dev.cfan.blogapi.repository.CategoryRepository;
import dev.cfan.blogapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    PostRepository postRepository;

    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    public List<Post> getCategoryPosts(Long categoryId) {
        return postRepository.findByCategoryId(categoryId);
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
}
