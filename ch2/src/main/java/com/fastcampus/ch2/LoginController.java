package com.fastcampus.ch2;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URLEncoder;

@Controller
@RequestMapping("/login")
public class LoginController {
    //@RequestMapping("/login/login")
    //@GetMapping("login/login")
    @GetMapping("/login")
    public String showLogin(){
        return "login";
    }

    //하나의 메서드로 GET,POST를 둘다 처리하는 경우
    //    @RequestMapping(value="/login/loging", method={RequestMethod.GET,RequestMethod.POST})

    //@RequestMapping(value="/login/login", method= RequestMethod.POST)
    //@PostMapping("/login/login")
    @PostMapping("/login")
    public String login(Model model,String id, String pwd, RedirectAttributes redirectAttribites)throws Exception{
        //1. id, pwd를 확인
        if(loginCheck(id,pwd)){
            //2. 일치하면, userInfo.html
            model.addAttribute("id",id);
            model.addAttribute("pwd",pwd);
            return "userInfo";
        }else{
            //3. 일치하지 않으면, login.html
            //String msg = URLEncoder.encode("id 또는 pwd가 일치하지 않습니다.");
            String msg = "id 또는 pwd가 일치하지 않습니다";
            redirectAttribites.addAttribute("msg", msg);
            redirectAttribites.addFlashAttribute("msg", "일회용 메세지");
            redirectAttribites.addFlashAttribute("msg","request 에 저장된 msg");
            //setAttribute는 저장, getAttribute는 데이터를 가져오는것.
            return "redirect:/";
            //redirect의 경우 GET 요청이다.
            //return "redirect:/login/login?msg="+msg;
            //redirect의 경우 GET 요청이다.

        }
    }

    private boolean loginCheck(String id, String pwd) {
    return id.equals("asdf")&&pwd.equals("1234");
    }

}
