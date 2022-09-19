package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name = "PersonModel")
public class PersonModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    @Size(min = 2, message = "Поля должны содержать не менее 2 символов")
    private String FirstName, SecondName, Otch;

    @Size(min = 4, message = "Поле должно содержать не менее 5 символов")
    private String Dolzh;

    @Email(message = "Некорректно введена электронная почта")
    private String Email;

    @Size(min = 10, max = 11, message = "Некорректно введен телефон")
    private String Phone;


    @ManyToOne(optional = true)
    private BaseModel ownerBasePersons;

    public PersonModel() {
    }

    public PersonModel(String firstName, String secondName, String otch, String dolzh, String email, String phone) {
        FirstName = firstName;
        SecondName = secondName;
        Otch = otch;
        Dolzh = dolzh;
        Email = email;
        Phone = phone;
    }

    public PersonModel(String firstName, String secondName, String otch, String dolzh, String email, String phone, BaseModel ownerBasePerson) {
        FirstName = firstName;
        SecondName = secondName;
        Otch = otch;
        Dolzh = dolzh;
        Email = email;
        Phone = phone;
        this.ownerBasePersons = ownerBasePerson;
    }

    public String getFullName() {
        return FirstName + " " + SecondName + " " + Otch;
    }

    public BaseModel getOwnerBasePerson() {
        return ownerBasePersons;
    }

    public void setOwnerBasePerson(BaseModel ownerBasePerson) {
        this.ownerBasePersons = ownerBasePerson;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getSecondName() {
        return SecondName;
    }

    public void setSecondName(String secondName) {
        SecondName = secondName;
    }

    public String getOtch() {
        return Otch;
    }

    public void setOtch(String otch) {
        Otch = otch;
    }

    public String getDolzh() {
        return Dolzh;
    }

    public void setDolzh(String dolzh) {
        Dolzh = dolzh;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
