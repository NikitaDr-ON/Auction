package com.RGR.Auction.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.*;

@Entity
@Table(name="users")
public class Data implements UserDetails {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private long id;
    @Column(name="Name")
    private String name;
    @Column(name="Surname")
    private String surname;
    @Column(name="Fathername")
    private String fathername;
    @Column(name="Birthdate")
    private Date birthdate;
    @Column(name="Gender")
    private int gender;
    @Column(name="Phone")
    private String phone;
    @Column(name="Mail")
    private String mail;
    @Column(name="Address")
    private String address;
    @Column(name="cardinfo")
    private String cardInfo;
    @Column(name="Password")
    private String password;
    @Column(name="Activation_code")
    private String activationCode;
    @Column(name="Activation")
    private String activation;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public Data() {
    }

    public Data(String name, String surname,String fathername, Date birthdate,int gender,String phone,
                String mail,String address,String cardInfo, String password) {
        this.name = name;
        this.surname = surname;
        this.fathername = fathername;
        this.birthdate=birthdate;
        this.gender=gender;
        this.phone=phone;
        this.mail = mail;
        this.address=address;
        this.cardInfo=cardInfo;
        this.password = password;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRole();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }

    public String getActivationCode() {
        return activationCode;
    }
    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public Set<Role> getRole() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getAddress() {
        return address;
    }

    public String getCardInfo() {
        return cardInfo;
    }
}
