package com.example.demo.controller;

import com.example.demo.model.AccFavRoomModel;
import com.example.demo.model.AccountModel;
import com.example.demo.rep.AccFavRoomModelRep;
import com.example.demo.rep.AccountModelRep;
import com.example.demo.rep.UserModelRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class AccountHomeController {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    @Autowired
    AccountModelRep accountModelRep;

    @Autowired
    UserModelRep userModelRep;

    @Autowired
    AccFavRoomModelRep accFavRoomModelRep;

    @GetMapping("/")
    public String AccountHomeGet(Model model, @AuthenticationPrincipal UserDetails currentUser){

        AccountModel Acc = accountModelRep.findByUser((
                userModelRep.findByUsername(currentUser.getUsername())));

        model.addAttribute("Account", Acc);
        return "account/AccountHome";
    }

    @PostMapping("/{id}/DeleteFav")
    public String FavDelete(@PathVariable("id")Long id, Model model){
        AccFavRoomModel fav = accFavRoomModelRep.findById(id).get();

        fav.setAccount(null);
        fav.setRoom(null);

        accFavRoomModelRep.save(fav);

        accFavRoomModelRep.delete(fav);
        return "redirect:/account/";
    }
}
