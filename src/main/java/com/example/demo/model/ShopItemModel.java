package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "ShopItemModel")
public class ShopItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Size(min = 3, message = "Должно быть минимум 3 символа")
    private String title, type;

    @Size(min = 10, message = "В описании должно быть минимум 10 символов")
    private String description;
    @NotNull(message = "Введите цену")
    private int price;

    @OneToMany (mappedBy = "shopItemOffer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<BaseShopItemModel> baseOffers;

    @OneToMany(mappedBy = "ownerItemShopDesc", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<ShopItemDescModel> ExtDesc;

    public ShopItemModel() {
    }

    public ShopItemModel(String title, String description, int price, String type, Collection<ShopItemDescModel> extDesc) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.type = type;
        ExtDesc = extDesc;
    }


    public ShopItemModel(String title, String description, String type, int price, Collection<BaseShopItemModel> baseOffers, Collection<ShopItemDescModel> extDesc) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.price = price;
        this.baseOffers = baseOffers;
        ExtDesc = extDesc;
    }

    public ShopItemModel(String title, String description, String type, int price) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.price = price;
    }

    public Collection<BaseShopItemModel> getBaseOffers() {
        return baseOffers;
    }

    public void setBaseOffers(Collection<BaseShopItemModel> baseOffers) {
        this.baseOffers = baseOffers;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Collection<ShopItemDescModel> getExtDesc() {
        return ExtDesc;
    }

    public void setExtDesc(Collection<ShopItemDescModel> extDesc) {
        ExtDesc = extDesc;
    }
}
