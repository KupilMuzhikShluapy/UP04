package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.rep.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/base")
public class BaseController {

    @Autowired
    BaseModelRep baseModelRep;

    @Autowired
    RoomModelRep roomModelRep;

    @Autowired
    BaseUslugiModelRep baseUslugiModelRep;

    @Autowired
    BaseShopItemModelRep baseShopItemModelRep;

    @Autowired
    ShopItemModelRep shopItemModelRep;

    public Long BaseId;

    @GetMapping("/")
    public String BaseHomeGet(Model model){

        Iterable<BaseModel> AllBase = baseModelRep.findAll();
        model.addAttribute("AllBase", AllBase);

        return "base/HomeBase";
    }

    //region DescMethod
    @GetMapping("/{id}")
    public String ViewBase(@PathVariable("id")Long id, Model model){
        Optional<BaseModel> ItemBase = baseModelRep.findById(id);

        model.addAttribute("ItemBase",ItemBase.get());

        BaseId = id;

        return "Base/BaseDesc";
    }
    //endregion

    //region AddMethod
    @GetMapping("/{id}/RoomAdd")
    public String RoomAddGet(@PathVariable("id") Long idBase, @Valid RoomModel Room, Model model){

        RoomModel RoomMod = new RoomModel();

        RoomMod.setOwner(baseModelRep.findById(idBase).get());

        model.addAttribute("Room", RoomMod);
        return "base/RoomAdd";
    }

    @PostMapping("/{owner_id}/RoomAdd")
    public String RoomAddPost(
            @PathVariable("owner_id") Long idBase,
            @Valid @ModelAttribute("Room") RoomModel Room,
            BindingResult bindingResult,
            Model model){

        if (bindingResult.hasErrors()){
            return "base/RoomAdd";
        }

        Room.setOwner(baseModelRep.findById(idBase).get());

        roomModelRep.save(Room);

        return "redirect:/base/" + idBase;
    }

    @GetMapping("/{id}/UslugaAdd")
    public String UslugaAddGet(@PathVariable("id") Long idBase, @Valid BaseUslugiModel Usluga, Model model){

        BaseUslugiModel UslugaMod = new BaseUslugiModel();

        UslugaMod.setOwnerBaseUslugi(baseModelRep.findById(idBase).get());

        model.addAttribute("Usluga", UslugaMod);
        return "base/UslugiAdd";
    }

    @PostMapping("/{owner_id}/UslugaAdd")
    public String UslugaAddPost(
            @PathVariable("owner_id") Long idBase,
            @Valid @ModelAttribute("Usluga") BaseUslugiModel Usluga,
            BindingResult bindingResult,
            Model model){

        if (bindingResult.hasErrors()){
            return "base/UslugiAdd";
        }

        Usluga.setOwnerBaseUslugi(baseModelRep.findById(idBase).get());

        baseUslugiModelRep.save(Usluga);

        return "redirect:/base/" + idBase;
    }

    @GetMapping("/{id}/ItemShopOfferAdd")
    public String ItemShopAddGet(@PathVariable("id") Long idBase, @Valid BaseShopItemModel offer, Model model){

        BaseShopItemModel OfferMod = new BaseShopItemModel();

        OfferMod.setBaseOffer(baseModelRep.findById(idBase).get());

        model.addAttribute("Offer", OfferMod);
        model.addAttribute("AllItemShop", shopItemModelRep.findAll());

        return "base/OfferAdd";
    }

    @PostMapping("/{owner_id}/ItemShopOfferAdd")
    public String ItemShopAddPost(
            @PathVariable("owner_id") Long idBase,
            @Valid @ModelAttribute("offer") BaseShopItemModel offer,
            @RequestParam("shopItemId") Long ItemId,
            BindingResult bindingResult,
            Model model){

        if (bindingResult.hasErrors()){
            return "base/OfferAdd";
        }

        offer.setBaseOffer(baseModelRep.findById(idBase).get());

        offer.setShopItemOffer(shopItemModelRep.findById(ItemId).get());

        baseShopItemModelRep.save(offer);

        return "redirect:/base/" + idBase;
    }

    @GetMapping("/BaseAdd")
    public String BaseAddGet(@Valid BaseModel Base, Model model){

        model.addAttribute("Base",new BaseModel());
        return "base/BaseAdd";
    }

    @PostMapping("/BaseAdd")
    public String BaseAddPost(
            @Valid @ModelAttribute("Base") BaseModel Base,
            BindingResult bindingResult,
            Model model){

        if (bindingResult.hasErrors()){
            return "base/BaseAdd";
        }

        baseModelRep.save(Base);

        return "redirect:/base/";
    }

    //endregion

    //region DeleteMethod
    @PostMapping("/{id}/DeleteBase")
    public String BaseDelete(@PathVariable("id")Long id, Model model){
        BaseModel Base = baseModelRep.findById(id).orElseThrow();
        baseModelRep.delete(Base);
        return "redirect:/base/";
    }

    @PostMapping("/{id}/DeleteRoom")
    public String RoomDelete(@PathVariable("id")Long id, Model model){
        RoomModel Room = roomModelRep.findById(id).orElseThrow();
        roomModelRep.delete(Room);
        return "redirect:/base/" + BaseId;
    }

    @PostMapping("/{id}/DeleteUsluga")
    public String UslugaDelete(@PathVariable("id")Long id, Model model){
        BaseUslugiModel Usluga = baseUslugiModelRep.findById(id).orElseThrow();
        baseUslugiModelRep.delete(Usluga);

        return "redirect:/base/" + BaseId;
    }
    //endregion



}
