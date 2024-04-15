package com.fastcampus.ch2;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    public String test(Model model, HttpServletRequest request){
        request.setAttribute("year", 2022);

        HttpSession session=request.getSession();
        session.setAttribute("id","asdf");

        ServletContext application = session.getServletContext();
        application.setAttribute("email","service@fastcampus.com");

        model.addAttribute("lastName", "Ina");
        model.addAttribute("firstName", "Jeong");
        model.addAttribute("list", Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee"));
        model.addAttribute("myFavorite", Arrays.asList("Summer", "Blue", "JunSeung"));
        model.addAttribute("bno", 123);
        model.addAttribute("user",new User("aaa",24));
        return "test";//templates/test.html
    }
}
