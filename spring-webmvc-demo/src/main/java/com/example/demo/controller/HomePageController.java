package com.example.demo.controller;

import com.example.demo.viewmodel.UserDetail;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomePageController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/greeting/{name}")
    public String greetingByName(@PathVariable("name") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(UserDetail userDetail) {
        String name = userDetail.getFirstName();
        return "redirect:/greeting/" + name;
    }

}
