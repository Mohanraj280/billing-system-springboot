package com.retail.billingSystem.Controller;

import com.retail.billingSystem.Model.AuthenticationModel;
import com.retail.billingSystem.ServiceLayer.AuthenticationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticatorController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session)
    {
        boolean authenticated = authenticationService.authenticateUser(email,password,session);
        return authenticated?"Login Successful":"Invalid Credentials";
    }


    @PostMapping("/register")
    public String registerUser(@RequestParam String email,@RequestParam String password)
    {
        String registerStatus = authenticationService.registerUser(email,password);
        return registerStatus;
    }
}
