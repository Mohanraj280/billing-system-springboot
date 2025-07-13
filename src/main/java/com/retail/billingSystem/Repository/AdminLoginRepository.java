package com.retail.billingSystem.Repository;

import com.retail.billingSystem.Model.AuthenticationModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminLoginRepository extends MongoRepository<AuthenticationModel, String> {

    AuthenticationModel findByEmail(String username);
}
