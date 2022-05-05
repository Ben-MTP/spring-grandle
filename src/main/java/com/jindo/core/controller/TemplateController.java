package com.jindo.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ManhKM on 5/5/2022
 * @project spring-grandle
 */
@Controller
public class TemplateController {

    @GetMapping(value = "/")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }
}
