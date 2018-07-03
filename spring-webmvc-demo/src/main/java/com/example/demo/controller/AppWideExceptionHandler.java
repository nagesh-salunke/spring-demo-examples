package com.example.demo.controller;

import com.example.demo.exceptions.RestrictedUserException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler(RestrictedUserException.class)
    public ModelAndView handleRestrictedUserException(RestrictedUserException e){
        ModelAndView modelAndView = new ModelAndView("restricted");
        modelAndView.addObject("name", e.getMessage());
        return modelAndView;
    }
}
