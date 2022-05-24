package com.RGR.Auction.models;

import javax.persistence.*;

@Entity
@Table(name="lot")
public class Lot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="Coast")
    private int coast;
    @Column(name="Seller")
    private int seller;
    @Column(name="Product_name")
    private String product;
    @Column(name="Description")
    private String description;
    @Column(name="Category")
    private int category;

    public Lot(long id, int coast, int seller, String product, String description, int category) {
        this.id = id;
        this.coast = coast;
        this.seller = seller;
        this.product = product;
        this.description = description;
        this.category = category;
    }

    public Lot() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCoast() {
        return coast;
    }

    public void setCoast(int coast) {
        this.coast = coast;
    }

    public int getSeller() {
        return seller;
    }

    public void setSeller(int seller) {
        this.seller = seller;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
