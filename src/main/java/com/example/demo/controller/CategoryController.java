package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path= "categories")
//http://localhost:8080/categories
public class CategoryController {
    @Autowired // Inject "CategoryRepository" - dependency injection
    private CategoryRepository categoryRepository;


    @RequestMapping(value="",method = RequestMethod.GET)
    public String getAllCategories(ModelMap modelMap){
        Iterable<Category> categories = categoryRepository.findAll();
        modelMap.addAttribute("categories",categories);
        for(Category x:categories)
            System.out.println(x);
        return "template";
    }
}
