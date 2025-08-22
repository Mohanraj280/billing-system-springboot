package com.retail.billingSystem.ServiceLayer;


import com.retail.billingSystem.Model.CategoryEntity;
import com.retail.billingSystem.Model.IO.CategoryDto;
import com.retail.billingSystem.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private static final String UPLOAD_DIR = "C:/Users/Public/billingSystem/uploads/";


    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryEntity addCategory(String name, String description, String bgColor, MultipartFile image) throws IOException
    {

//        String fileName = UUID.randomUUID()+"_"+image.getOriginalFilename();
//        File file = new File(UPLOAD_DIR + fileName);
//        file.getParentFile().mkdirs();
//        image.transferTo(file);

//        String fileName = image.getOriginalFilename();
//        Path filePath = Paths.get("uploads", fileName);
//        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//
//// Save this in imgUrl field:
//        String imageUrl = "http://localhost:8080/uploads/" + fileName;
//
        byte[] imageBytes = image.getBytes();  // Read bytes from image
        String contentType = image.getContentType();  // e.g. image/png


        Date now=new Date();

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(name);
        categoryEntity.setDescription(description);
        categoryEntity.setBgColor(bgColor);
//        categoryEntity.setImgUrl(imageUrl);

        categoryEntity.setImageData(imageBytes);
        categoryEntity.setImageType(contentType);
        categoryEntity.setCreatedAt(now);
        categoryEntity.setUpdatedAt(now);
        return categoryRepository.save(categoryEntity);
    }

    public List<CategoryDto> getAllCategories() {
        List<CategoryEntity> entities = categoryRepository.findAll();

        return entities.stream().map(entity -> {
            CategoryDto dto = new CategoryDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setBgColor(entity.getBgColor());
            dto.setImageUrl("http://localhost:8080/categories/image/" + entity.getId()); // image endpoint
            dto.setCreatedAt(entity.getCreatedAt());
            dto.setUpdatedAt(entity.getUpdatedAt());
            return dto;
        }).collect(Collectors.toList());
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
