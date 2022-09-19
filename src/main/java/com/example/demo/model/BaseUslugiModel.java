package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "BaseUslugiModel")
public class BaseUslugiModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    private int price;

    @Size(min = 3, message = "Должно быть минимум 3 символа")
    private String title;

    @Size(min = 10, message = "В описании должно быть минимум 10 символов")
    private String description;

    @ManyToOne()
    private BaseModel ownerBaseUslugi;

    public BaseUslugiModel() {
    }


    public BaseUslugiModel(String title, String description, int price, BaseModel ownerBaseUslugi) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.ownerBaseUslugi = ownerBaseUslugi;
    }

    public BaseModel getOwnerBaseUslugi() {
        return ownerBaseUslugi;
    }

    public void setOwnerBaseUslugi(BaseModel ownerBaseUslugi) {
        this.ownerBaseUslugi = ownerBaseUslugi;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
}
