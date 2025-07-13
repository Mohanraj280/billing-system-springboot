package com.retail.billingSystem.Controller;


import com.retail.billingSystem.Model.CategoryEntity;
import com.retail.billingSystem.ServiceLayer.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public CategoryEntity addCategory(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("bgColor") String bgColor,
            @RequestParam("image") MultipartFile image) throws IOException
    {
        return categoryService.addCategory(name, description, bgColor, image);
    }


    @GetMapping("/allCategories")
    public List<CategoryEntity> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable String id)
    {
        return categoryService.deleteCategory(id);
    }

    @GetMapping("/search")
    public List<CategoryEntity> searchCategory(@RequestParam String keyword)
    {
        return categoryService.searchCategory(keyword);
    }

}
