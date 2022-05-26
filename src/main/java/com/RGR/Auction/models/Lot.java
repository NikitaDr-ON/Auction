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
    @ManyToOne   
    @JoinColumn(name="Seller")  
    private Data seller;
    @Column(name="Product_name")
    private String product;
    @Column(name="Description")
    private String description;
    @ManyToOne   
    @JoinColumn(name="Category")  
    private CategoryModel category;

    public Lot(long id, int coast, Data seller, String product, String description, CategoryModel category) {
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

    public Data getSeller() {
        return seller;
    }

    public void setSeller(Data seller) {
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

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory( CategoryModel category) {
        this.category = category;
    }
}
