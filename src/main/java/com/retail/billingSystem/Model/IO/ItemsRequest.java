package com.retail.billingSystem.Model.IO;

import com.retail.billingSystem.Model.CategoryEntity;

public class ItemsRequest {
    private String name;
    private String categoryId;
    private Double price;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryEntity) {
        this.categoryId = categoryEntity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
