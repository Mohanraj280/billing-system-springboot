package com.retail.billingSystem.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ParentController {

    @RequestMapping("/home")
    public String getHome()
    {
        return "index.html";
    }
}
