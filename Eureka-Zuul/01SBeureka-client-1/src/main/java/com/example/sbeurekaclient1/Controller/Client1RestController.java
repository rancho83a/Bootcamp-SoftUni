package com.example.sbeurekaclient1.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Client1RestController {

    @GetMapping("/name")
    public String name(){
        return "My name is ...";
    }
}
