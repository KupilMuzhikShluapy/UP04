package com.example.demo.controller;

import com.example.demo.model.AccountModel;
import com.example.demo.model.Role;
import com.example.demo.model.UserModel;
import com.example.demo.rep.AccountModelRep;
import com.example.demo.rep.UserModelRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class AccountController {
    @Autowired
    private UserModelRep userRepository;

    @Autowired
    private AccountModelRep accountModelRep;

    //Authentication auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

    @GetMapping("/registration")
    public String registration(){
        return "register";
    }

    @PostMapping("/registration")
    public String addUser(UserModel user, Model model){
        UserModel userFromDb = userRepository.findByUsername(user.getUsername());
        if(userFromDb != null)
        {
            model.addAttribute("message", "Пользователь с таким логином уже зарегистрирован");
            return "register";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        accountModelRep.save(new AccountModel(user));

        return "redirect:/login";
    }


}
