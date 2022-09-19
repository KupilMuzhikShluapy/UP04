package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.rep.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomModelRep roomModelRep;
    @Autowired
    StuffModelRep stuffModelRep;
    @Autowired
    AccountModelRep accountModelRep;
    @Autowired
    UserModelRep userModelRep;
    @Autowired
    AccFavRoomModelRep accFavRoomModelRep;

    public Long IdRoom;

    @GetMapping("/{id}")
    public String ViewRoom(@PathVariable("id")Long id, Model model, @AuthenticationPrincipal UserDetails currentUser){

        IdRoom = id;

        Optional<RoomModel> ItemRoom = roomModelRep.findById(id);

        model.addAttribute("ItemRoom",ItemRoom.get());

        AccountModel Acc = accountModelRep.findByUser((
                userModelRep.findByUsername(currentUser.getUsername())));

        if (accFavRoomModelRep.findByAccount_IdAndRoom_Id(Acc.getId(), ItemRoom.get().getId()) != null)
        {
            model.addAttribute("AddFav",false);
        }else{
            model.addAttribute("AddFav",true);
        }

        return "base/room/RoomDesc";
    }

    @PostMapping("/{id}/DeleteRoom")
    public String RoomDelete(@PathVariable("id")Long id, Model model){
        RoomModel Room = roomModelRep.findById(id).orElseThrow();
        roomModelRep.delete(Room);
        return "redirect:/base/";
    }

    @PostMapping("/{id}/DeleteStuff")
    public String StuffDelete(@PathVariable("id")Long id, Model model){
        StuffModel Stuff = stuffModelRep.findById(id).orElseThrow();
        stuffModelRep.delete(Stuff);
        return "redirect:/room/" + IdRoom;
    }

    //region AddMethod
    @GetMapping("/{id}/StuffAdd")
    public String StuffAddGet(@PathVariable("id") Long idRoom, @Valid StuffModel Stuff, Model model){

        StuffModel StuffMode = new StuffModel();

        StuffMode.setOwnerRoomStuff(roomModelRep.findById(idRoom).get());

        model.addAttribute("Stuff", StuffMode);
        return "base/room/StuffAdd";
    }



    @PostMapping("/{OwnerRoomStuff_id}/StuffAdd")
    public String StuffAddPost(
            @PathVariable("OwnerRoomStuff_id") Long idRoom,
            @Valid @ModelAttribute("Stuff") StuffModel Stuff,
            BindingResult bindingResult,
            Model model){

        if (bindingResult.hasErrors()){
            return "base/room/StuffAdd";
        }

        Stuff.setOwnerRoomStuff(roomModelRep.findById(idRoom).get());

        stuffModelRep.save(Stuff);

        return "redirect:/room/" + IdRoom;
    }

    @GetMapping("/{OwnerRoomStuff_id}/FavAdd")
    public String FavAddPost(
            @PathVariable("OwnerRoomStuff_id") Long idRoom,
            @AuthenticationPrincipal UserDetails currentUser,
            Model model){

        RoomModel ItemRoom = roomModelRep.findById(idRoom).get();

        AccountModel Acc = accountModelRep.findByUser((
                userModelRep.findByUsername(currentUser.getUsername())));

        AccFavRoomModel NewFav = new AccFavRoomModel(Acc, ItemRoom);

        accFavRoomModelRep.save(NewFav);

        return "redirect:/base/";
    }

    //endregion

}
