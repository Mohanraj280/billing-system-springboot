package com.retail.billingSystem.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "authentication")
public class AuthenticationModel {


    @Id
    public String Id;

    public String email;

    public String password;

    public String role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role)
    {
        this.role = role;
    }
    public String getRole()
    {
        return role;
    }
}
