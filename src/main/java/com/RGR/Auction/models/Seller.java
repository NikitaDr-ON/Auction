package com.RGR.Auction.models;
import java.util.Date;
import javax.persistence.Column;

public class Seller {
    private long id;
    private String firstname;
    private String surname;
    private String fathername;
    private Date birthdate;
    private String gender;
    private String phone;
    private String email;
    private String cardinfo;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCardinfo() {
        return cardinfo;
    }

    public void setCardinfo(String cardinfo) {
        this.cardinfo = cardinfo;
    }
}
