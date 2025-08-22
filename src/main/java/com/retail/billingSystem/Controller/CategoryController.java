package com.retail.billingSystem.Controller;


import com.retail.billingSystem.Model.CategoryEntity;
import com.retail.billingSystem.Model.IO.CategoryDto;
import com.retail.billingSystem.Model.IO.CategoryRequest;
import com.retail.billingSystem.Repository.CategoryRepository;
import com.retail.billingSystem.ServiceLayer.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/add")
    public CategoryEntity addCategory(
//            @RequestParam("name") String name,
//            @RequestParam("description") String description,
//            @RequestParam("bgColor") String bgColor,
            @ModelAttribute CategoryRequest categoryRequest,
            @RequestParam("image") MultipartFile image) throws IOException
    {
        return categoryService.addCategory(categoryRequest.getName(), categoryRequest.getDescription(), categoryRequest.getBgColor(), image);
    }


    @GetMapping("/allCategories")
    public List<CategoryDto> getAllCategories() {
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

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String id) {
        Optional<CategoryEntity> optional = categoryRepository.findById(id);
        if (optional.isPresent()) {
            CategoryEntity category = optional.get();
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(category.getImageType()))
                    .body(category.getImageData());
        }
        return ResponseEntity.notFound().build();
    }
}
