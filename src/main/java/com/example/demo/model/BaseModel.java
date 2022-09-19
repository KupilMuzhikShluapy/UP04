package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, message = "Данные не могут быть меньше 3 символов")
    public String Street, Description, title;

    @Size(min = 1, message = "Введите дом")
    public String House;

    @OneToMany (mappedBy = "baseOffer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<BaseShopItemModel> shopItemOffers;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<RoomModel> Rooms;

    @OneToMany(mappedBy = "ownerBasePersons", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<PersonModel> Persons;

    @OneToMany(mappedBy = "ownerBaseUslugi", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<BaseUslugiModel> Uslugi;

    public String GetStreetAndHome(){return Street + ", " + House;}

    public BaseModel() {
    }

    public BaseModel(String street, String house, String description, String title) {
        Street = street;
        House = house;
        Description = description;
        this.title = title;
    }

    public BaseModel(String street, String house, String description, String title, Collection<RoomModel> rooms) {
        Street = street;
        House = house;
        Description = description;
        this.title = title;
        Rooms = rooms;
    }

    public BaseModel(String street, String description, String title, String house, Collection<RoomModel> rooms, Collection<PersonModel> persons, Collection<BaseUslugiModel> uslugi) {
        Street = street;
        Description = description;
        this.title = title;
        House = house;
        Rooms = rooms;
        Persons = persons;
        Uslugi = uslugi;
    }

    public BaseModel(String street, String description, String title, String house, Collection<BaseShopItemModel> shopItemOffers, Collection<RoomModel> rooms, Collection<PersonModel> persons, Collection<BaseUslugiModel> uslugi) {
        Street = street;
        Description = description;
        this.title = title;
        House = house;
        this.shopItemOffers = shopItemOffers;
        Rooms = rooms;
        Persons = persons;
        Uslugi = uslugi;
    }

    public Collection<BaseShopItemModel> getShopItemOffers() {
        return shopItemOffers;
    }

    public void setShopItemOffers(Collection<BaseShopItemModel> shopItemOffers) {
        this.shopItemOffers = shopItemOffers;
    }

    public Collection<BaseUslugiModel> getUslugi() {
        return Uslugi;
    }

    public void setUslugi(Collection<BaseUslugiModel> uslugi) {
        Uslugi = uslugi;
    }

    public Collection<PersonModel> getPersons() {
        return Persons;
    }

    public void setPersons(Collection<PersonModel> persons) {
        Persons = persons;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getHouse() {
        return House;
    }

    public void setHouse(String house) {
        House = house;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<RoomModel> getRooms() {
        return Rooms;
    }

    public void setRooms(Collection<RoomModel> rooms) {
        Rooms = rooms;
    }
}
