package com.jindo.core.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ManhKM on 5/5/2022
 * @project spring-grandle
 */
@RestController
public class HomePageController {

    @GetMapping(value = "/v1")
    public String home(){
        return "Welcome to Spring Boot Webservice";
    }

    @GetMapping(value = "/quickstart")
    public String quickstart(){
        return "Hello";
    }


}
