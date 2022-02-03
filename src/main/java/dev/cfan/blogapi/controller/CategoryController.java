package dev.cfan.blogapi.controller;

import dev.cfan.blogapi.domain.Category;
import dev.cfan.blogapi.domain.Post;
import dev.cfan.blogapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }


    @GetMapping("/categories")
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

    @GetMapping("/categories/{categoryId}")
    public List<Post> getCategoryPosts(@PathVariable(value = "categoryId") Long categoryId){
        return categoryService.getCategoryPosts(categoryId);
    }

    @DeleteMapping("/categories/{categoryId}" )
    public Category deleteCategory(@PathVariable(value = "categoryId") Long categoryId){
        return categoryService.deleteCategory(categoryId);
    }

}
