package com.retail.billingSystem.Repository;

import com.retail.billingSystem.Model.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<OrderEntity,String> {
}
