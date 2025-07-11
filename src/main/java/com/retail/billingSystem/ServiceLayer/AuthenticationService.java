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

//    @PostConstruct
//    public void registerUser()
//    {
////        if (adminLoginRepository.findByEmail(email) != null) {
////            throw new RuntimeException("User already exists with email: " + email);
////        }
//        String password = "1234";
//        String hashedPassword = passwordEncoder.encode(password);
//
//        AuthenticationModel user = new AuthenticationModel();
//        user.setEmail("mohanrajs.2828@gmail.com");
//        user.setPassword(hashedPassword);
//        user.setRole("Admin");
//
//
//        adminLoginRepository.save(user);
//    }

    public boolean authenticateUser(String email, String plainPassword,HttpSession session)
    {
        AuthenticationModel user = adminLoginRepository.findByEmail(email);

        if (user != null && passwordEncoder.matches(plainPassword, user.getPassword())) {
            // Authentication success: store in session
            session.setAttribute("email", user.getEmail());
            session.setAttribute("role", user.getRole());
//            session.setAttribute("password", user.getPassword()); // optional, not recommended to store plain password

            return true;
        }
        return false;
    }
}
