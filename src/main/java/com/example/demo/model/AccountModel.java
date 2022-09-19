package com.example.demo.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "AccountModel")
public class AccountModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private Collection<AccFavRoomModel> favRooms;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private UserModel user;

    public AccountModel() {
    }

    public AccountModel(UserModel user) {
        this.user = user;
    }

    public AccountModel(Collection<AccFavRoomModel> favRooms, UserModel user) {
        this.favRooms = favRooms;
        this.user = user;
    }

    public Collection<AccFavRoomModel> getFavRooms() {
        return favRooms;
    }

    public void setFavRooms(Collection<AccFavRoomModel> favRooms) {
        this.favRooms = favRooms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
