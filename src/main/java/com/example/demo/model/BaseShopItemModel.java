package com.example.demo.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "BaseShopItemModel")
public class BaseShopItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(optional = true)
    private BaseModel baseOffer;

    @ManyToOne(optional = true)
    private ShopItemModel shopItemOffer;

    private int count;

    public BaseShopItemModel() {
    }

    public BaseShopItemModel(BaseModel baseOffer, ShopItemModel shopItemOffer, int count) {
        this.baseOffer = baseOffer;
        this.shopItemOffer = shopItemOffer;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BaseModel getBaseOffer() {
        return baseOffer;
    }

    public void setBaseOffer(BaseModel baseOffer) {
        this.baseOffer = baseOffer;
    }

    public ShopItemModel getShopItemOffer() {
        return shopItemOffer;
    }

    public void setShopItemOffer(ShopItemModel shopItemOffer) {
        this.shopItemOffer = shopItemOffer;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
