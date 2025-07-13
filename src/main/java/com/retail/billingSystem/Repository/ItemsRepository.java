package com.retail.billingSystem.Repository;

import com.retail.billingSystem.Model.CategoryEntity;
import com.retail.billingSystem.Model.ItemsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ItemsRepository extends MongoRepository<ItemsEntity,String> {
    List<ItemsEntity> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);

}
