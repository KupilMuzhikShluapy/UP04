package com.example.demo.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "AccFavRoomModel")
public class AccFavRoomModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(optional = true)
    private AccountModel account;

    @OneToOne(optional = true)
    @JoinColumn(name="roomFav_id")
    private RoomModel room;

    public AccFavRoomModel() {
    }

    public AccFavRoomModel(AccountModel account, RoomModel room) {
        this.account = account;
        this.room = room;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountModel getAccount() {
        return account;
    }

    public void setAccount(AccountModel account) {
        this.account = account;
    }

    public RoomModel getRoom() {
        return room;
    }

    public void setRoom(RoomModel room) {
        this.room = room;
    }
}
