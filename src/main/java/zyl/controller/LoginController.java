package zyl.controller;
import	java.security.acl.Group;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import zyl.model.User;
import zyl.service.*;

import javax.servlet.http.*;

@Controller
public class LoginController {
    @Autowired
    public LoginService loginService;
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/login")
    public String login(String username,String password,HttpServletRequest request) {
        User user = loginService.query123(username,password);
        if (user == null){
            System.out.println("-----------");
            return "login";
        }
        HttpSession session=request.getSession(true);
        System.out.println("===========");
        session.setAttribute("user",user);
        return "redirect:/";
    }
    @Autowired
    public RegisterService registryService;
    @GetMapping("/register")
    public String register(){
        return "register";
    }
    @PostMapping("/register")
    public String register(String username,String password,HttpServletRequest request){
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setNickname(username);
        user.setAvatar("https://picsum.photos/id/1/200/200");
        int num=registryService.addUser(user);
        return "login";
    }

}
