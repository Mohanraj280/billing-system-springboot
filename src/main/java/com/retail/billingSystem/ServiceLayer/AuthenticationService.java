package com.retail.billingSystem.ServiceLayer;

import com.retail.billingSystem.Model.AuthenticationModel;
import com.retail.billingSystem.Repository.AdminLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpSession;

@Service
public class AuthenticationService {

    @Autowired
    public AdminLoginRepository adminLoginRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public String registerUser(String email,String plainPassword)
    {
        if (adminLoginRepository.findByEmail(email) != null) {
            throw new RuntimeException("User already exists with email: " + email);
        }
        String hashedPassword = passwordEncoder.encode(plainPassword);

        AuthenticationModel user = new AuthenticationModel();
        user.setEmail(email);
        user.setPassword(hashedPassword);
        user.setRole("Employee");


        AuthenticationModel savedUser = adminLoginRepository.save(user);

        return "User registered successfully with ID: " + savedUser.getId();
    }

    public boolean authenticateUser(String email, String plainPassword,HttpSession session)
    {
        AuthenticationModel user = adminLoginRepository.findByEmail(email);

        if (user != null && passwordEncoder.matches(plainPassword, user.getPassword())) {
            // Authentication success: store in session
            session.setAttribute("email", user.getEmail());
            session.setAttribute("role", user.getRole());
//          session.setAttribute("password", user.getPassword());

            return true;
        }
        return false;
    }
}
