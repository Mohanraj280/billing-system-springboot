package com.retail.billingSystem.ServiceLayer;


import com.retail.billingSystem.Model.CategoryEntity;
import com.retail.billingSystem.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private static final String UPLOAD_DIR = "C:/Users/Public/billingSystem/uploads/";


    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryEntity addCategory(String name, String description, String bgColor, MultipartFile image) throws IOException
    {

        String fileName = UUID.randomUUID()+"_"+image.getOriginalFilename();
        File file = new File(UPLOAD_DIR + fileName);
        file.getParentFile().mkdirs();
        image.transferTo(file);

        Date now=new Date();

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(name);
        categoryEntity.setDescription(description);
        categoryEntity.setBgColor(bgColor);
        categoryEntity.setImgUrl("/" + UPLOAD_DIR + fileName);
        categoryEntity.setCreatedAt(now);
        categoryEntity.setUpdatedAt(now);
        return categoryRepository.save(categoryEntity);
    }

    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    public ResponseEntity<?> deleteCategory(String id)
    {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return ResponseEntity.ok("Category deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found.");
        }
    }

    public List<CategoryEntity> searchCategory(String keyword)
    {
        return categoryRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword,keyword);
    }
}
