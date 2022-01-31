package dev.cfan.blogapi.controller;

import dev.cfan.blogapi.domain.Category;
import dev.cfan.blogapi.domain.Post;
import dev.cfan.blogapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    @PostMapping("/new")
    public Category createCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }


    @GetMapping("/{categoryId}")
    public List<Post> getCategoryPosts(@PathVariable(value = "categoryId") Long categoryId){
        return categoryService.getCategoryPosts(categoryId);
    }

}
