package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.rep.ShopItemDescModelRep;
import com.example.demo.rep.ShopItemModelRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    ShopItemModelRep shopItemModelRep;

    @Autowired
    ShopItemDescModelRep shopItemDescModelRep;

    @GetMapping("/")
    public String ShopHomeGet(Model model){

        Iterable<ShopItemModel> AllItemShop = shopItemModelRep.findAll();
        model.addAttribute("AllItemShop", AllItemShop);

        return "shop/ShopHome";
    }

    @GetMapping("/ItemAdd")
    public String ItemAddGet(@Valid ShopItemModel ItemShop, Model model){

        model.addAttribute("ItemShop",new ShopItemModel());
        return "shop/ShopItemAdd";
    }

    @PostMapping("/ItemAdd")
    public String ItemAddPost(
            @Valid @ModelAttribute("ItemShop") ShopItemModel ItemShop,
            BindingResult bindingResult,
            Model model){

        if (bindingResult.hasErrors()){
            return "shop/ShopItemAdd";
        }

        shopItemModelRep.save(ItemShop);

        shopItemDescModelRep.save(new ShopItemDescModel("Общее описание:", ItemShop.getDescription(), ItemShop));

        return "redirect:/shop/";
    }

    @GetMapping("/{id}/DescAdd")
    public String DescAddGet(@PathVariable("id") Long idShopItem, @Valid ShopItemDescModel shopItemDesc, Model model){

        ShopItemDescModel ItemDesc = new ShopItemDescModel();

        ItemDesc.setOwnerItemShopDesc(shopItemModelRep.findById(idShopItem).get());

        model.addAttribute("ItemDesc", ItemDesc);
        return "shop/ShopItemDescAdd";
    }

    @PostMapping("/{owner_item_shop_desc_id}/DescAdd")
    public String DescAddPost(
            @PathVariable("owner_item_shop_desc_id") Long idShopItem,
            @Valid @ModelAttribute("ItemDesc") ShopItemDescModel shopItemDesc,
            BindingResult bindingResult,
            Model model){

        if (bindingResult.hasErrors()){
            return "shop/ShopItemDescAdd";
        }

        shopItemDesc.setOwnerItemShopDesc(shopItemModelRep.findById(idShopItem).get());

        shopItemDescModelRep.save(shopItemDesc);

        return "redirect:/shop/";
    }

    @PostMapping("/{id}/DeleteShopItem")
    public String ShopItemDelete(@PathVariable("id")Long id, Model model){
        ShopItemModel ShopItem = shopItemModelRep.findById(id).orElseThrow();
        shopItemModelRep.delete(ShopItem);
        return "redirect:/shop/";
    }



}
