package com.retail.billingSystem.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ParentController {

    @GetMapping("/home")
    public String getHome()
    {
        return "index.html";
    }
}
