package com.retail.billingSystem.Repository;

import com.retail.billingSystem.Model.CategoryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends MongoRepository<CategoryEntity, String> {
    List<CategoryEntity> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);

}
