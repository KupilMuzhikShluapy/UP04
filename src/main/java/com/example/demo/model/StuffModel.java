package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "StuffModel")
public class StuffModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Size(min = 3, message = "Должно быть минимум 3 символа")
    private String Title, Type;

    @Size(min = 10, message = "В описании должно быть минимум 10 символов")
    private String Description;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private RoomModel ownerRoomStuff;

    public StuffModel() {
    }

    public StuffModel(String type, String title, String description, RoomModel ownerRoomStuff) {
        Type = type;
        Title = title;
        Description = description;
        this.ownerRoomStuff = ownerRoomStuff;
    }

    public RoomModel getOwnerRoomStuff() {
        return ownerRoomStuff;
    }

    public void setOwnerRoomStuff(RoomModel ownerRoomStuff) {
        this.ownerRoomStuff = ownerRoomStuff;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
