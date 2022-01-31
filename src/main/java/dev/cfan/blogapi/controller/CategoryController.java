package dev.cfan.blogapi.controller;

import dev.cfan.blogapi.domain.Category;
import dev.cfan.blogapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    @PostMapping("/new")
    public Category createCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }

}
