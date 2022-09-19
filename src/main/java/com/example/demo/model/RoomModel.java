package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "RoomModel")
public class RoomModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, message = "Поля не должны быть пустыми")
    private String Title, Description;

    @OneToOne(optional = true, mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private AccFavRoomModel room;

    @ManyToOne(optional = true)
    private BaseModel owner;

    @OneToMany(mappedBy = "ownerRoomStuff", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<StuffModel> Stuff;

    public RoomModel() {
    }

    public RoomModel(String title, String description, BaseModel owner) {
        Title = title;
        Description = description;
        this.owner = owner;
    }

    public RoomModel(String title, String description) {
        Title = title;
        Description = description;
    }

    public Collection<StuffModel> getStuff() {
        return Stuff;
    }

    public void setStuff(Collection<StuffModel> stuff) {
        Stuff = stuff;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BaseModel getOwner() {
        return owner;
    }

    public void setOwner(BaseModel owner) {
        this.owner = owner;
    }
}
