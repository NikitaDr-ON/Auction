package com.RGR.Auction.models;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="users")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

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

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String name, String surname, String fathername, Date startDate, int gender, String phone, String mail, String address, String cardInfo, String password) {
    }

}