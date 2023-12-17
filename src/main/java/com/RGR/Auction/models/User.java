package com.RGR.Auction.models;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;

//@Entity
//@Table(name="programming_language")
public class User {
    int ID;
    String name;
    String surname;
    String fathername;
    Date birthdate;
    int gender;
    String phone;
    String mail;
    String address;
    String cardInfo;
    public String getMail() {
        return mail;
    }

    public int getId() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int ID) {
        this.ID = ID;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCardInfo() {
        return cardInfo;
    }
}

