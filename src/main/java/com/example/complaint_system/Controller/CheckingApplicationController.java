package com.example.complaint_system.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckingApplicationController {

    @GetMapping("Checking/")
    public String checking(){
        return "Server Started Successfully!";
    }
}
