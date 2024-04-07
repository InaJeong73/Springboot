package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String main(){
        return "index";//templates/index.html
    }
    @GetMapping("/test")
    public String test(Model model){
        model.addAttribute("lastName", "Ina");
        model.addAttribute("firstName", "Jeong");
        model.addAttribute("list", Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee"));
        return "test";//templates/test.html
    }

}
