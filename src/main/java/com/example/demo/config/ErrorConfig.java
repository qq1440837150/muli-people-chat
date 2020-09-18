package com.example.demo.config;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorConfig {
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(Exception e){
        ModelAndView modelAndView = new ModelAndView ();
        modelAndView.setViewName ("error");
        modelAndView.addObject ("error",e);
        return modelAndView;
    }
}
