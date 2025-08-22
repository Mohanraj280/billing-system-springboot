package com.retail.billingSystem.Controller;

import com.retail.billingSystem.Model.CategoryEntity;
import com.retail.billingSystem.Model.IO.ItemsRequest;
import com.retail.billingSystem.Model.ItemsEntity;
import com.retail.billingSystem.ServiceLayer.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/items")
@CrossOrigin("*")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    @PostMapping("/add")
    public ItemsEntity addItem(
//            @RequestParam("name") String name,
//            @RequestParam("categoryId") String categoryId,  // sending ID from frontend
//            @RequestParam("price") Double price,
//            @RequestParam("description") String description,
            @ModelAttribute ItemsRequest itemsRequest,
            @RequestParam("image") MultipartFile image
    )throws IOException
    {
        return itemsService.addItems(itemsRequest.getName(),itemsRequest.getCategoryId(),itemsRequest.getPrice(),itemsRequest.getDescription(),image);
    }

    @GetMapping("/allItems")
    public List<ItemsEntity> getAllItems() {
        return itemsService.getAllItems();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteItemById(@PathVariable String id)
    {
        return itemsService.deleteItem(id);
    }

    @GetMapping("/search")
    public List<ItemsEntity> searchItem(@RequestParam String keyword)
    {
        return itemsService.searchItem(keyword);
    }
}
