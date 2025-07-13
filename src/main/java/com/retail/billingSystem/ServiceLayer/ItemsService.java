package com.retail.billingSystem.ServiceLayer;


import com.retail.billingSystem.Model.CategoryEntity;
import com.retail.billingSystem.Model.ItemsEntity;
import com.retail.billingSystem.Repository.CategoryRepository;
import com.retail.billingSystem.Repository.ItemsRepository;
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
public class ItemsService {

    private static final String UPLOAD_DIR = "C:/Users/Public/billingSystem/itemuploads/";


    @Autowired
    private ItemsRepository itemsRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ItemsEntity addItems(String name, String categoryId,
    Double price, String description, MultipartFile image ) throws IOException
    {
        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new RuntimeException("Category Not Found"));
        String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
        File file = new File(UPLOAD_DIR + fileName);
        file.getParentFile().mkdirs();
        image.transferTo(file);

        Date now = new Date();

        ItemsEntity item=new ItemsEntity();
        item.setName(name);
        item.setCategoryEntity(category);
        item.setPrice(price);
        item.setDescription(description);
        item.setImageUrl("/" + UPLOAD_DIR + fileName);
        item.setCreatedAt(now);
        item.setUpdatedAt(now);
        return itemsRepository.save(item);
    }

    public List<ItemsEntity> getAllItems() {
        return itemsRepository.findAll();
    }

    public ResponseEntity<?> deleteItem(String id)
    {
        if (itemsRepository.existsById(id)) {
            itemsRepository.deleteById(id);
            return ResponseEntity.ok("Category deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found.");
        }
    }

    public List<ItemsEntity> searchItem(String keyword)
    {
        return itemsRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword,keyword);
    }
}
