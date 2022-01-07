package com.kyungmin.examplespringbootsecurity.controller;

import com.kyungmin.examplespringbootsecurity.entity.Role;
import com.kyungmin.examplespringbootsecurity.entity.User;
import com.kyungmin.examplespringbootsecurity.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RegistrationController { //사용자 요청을 처리하는 Main Controller

    @Autowired
    private RegistrationService registrationService;

    //회원가입 페이지로 이동
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {

        User user = new User();
        model.addAttribute("user", user);

        return "signup";
    }

    //회원가입 후 메인 페이지로 이동
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPost(@ModelAttribute("user") User user, Model model) {

        if (registrationService.checkEmailExists(user.getEmail())) {
            model.addAttribute("emailExists", true);
            return "signup";
        }
        else {
            Role role = registrationService.findByRolename("ROLE_USER");

            List<Role> userRoles = new ArrayList<>();
            userRoles.add(role);

            registrationService.createUser(user, userRoles);

            return "redirect:/";
        }
    }
}