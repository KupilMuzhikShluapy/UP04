package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ShopItemDescModel")
public class ShopItemDescModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Size(min = 3, message = "Должно быть минимум 3 символа")
    private String title;

    @Size(min = 4, message = "В описании должно быть минимум 4 символа")
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    private ShopItemModel ownerItemShopDesc;

    public ShopItemDescModel() {
    }

    public ShopItemDescModel(String title, String description, ShopItemModel ownerItemShopDesc) {
        this.title = title;
        this.description = description;
        this.ownerItemShopDesc = ownerItemShopDesc;
    }

    public ShopItemDescModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ShopItemModel getOwnerItemShopDesc() {
        return ownerItemShopDesc;
    }

    public void setOwnerItemShopDesc(ShopItemModel ownerItemShopDesc) {
        this.ownerItemShopDesc = ownerItemShopDesc;
    }
}
