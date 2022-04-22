package com.RGR.Auction.models;

import javax.persistence.*;

@Entity
@Table(name="users")
public class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    //@Column(name="Name")
    private String Name;
   // @Column(name="Surname")
    private String Surname;
   // @Column(name="Mail")
    private String Mail;
   // @Column(name="Password")
    private String Password;
  //  @Column(name="Role")
    private String Role;

    public Data() {
    }

    public Data(String name, String surname, String mail, String password) {
        this.Name = name;
        this.Surname = surname;
        this.Mail = mail;
        this.Password = password;
        this.Role="user";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        this.Surname = surname;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        this.Mail = mail;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        this.Role = role;
    }
}
