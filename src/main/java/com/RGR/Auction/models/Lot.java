package com.RGR.Auction.models;

import javax.persistence.*;
import java.sql.Array;
import java.util.*;


@Entity
@Table(name="lot")
public class Lot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_Product")
    private int id;
    @Column(name="Product_name")
    private String product;
    @Column(name="Start_cost")
    private int startCost;
    @Column(name="Seller")
    private Long seller;
    @Column(name="Description")
    private String description;
    @Column(name="Photo")
    private String photo;
    @Column(name="Category")
    private Long category;
    @ManyToMany(mappedBy = "lots")
    private List<Data> users=new ArrayList<>();

    public Lot(String product, int startCost, Long seller, String description, String photo, Long category) {
        this.product = product;
        this.startCost = startCost;
        this.seller = seller;
        this.description = description;
        this.category = category;
    }

    public Lot(String product, int startCost, Long seller, String description,  Long category) {
        this.product = product;
        this.startCost = startCost;
        this.seller = seller;
        this.description = description;
        this.category = category;
    }
    public Lot() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getStartCost() {
		return startCost;
	}

	public void setStartCost(int startCost) {
		this.startCost = startCost;
	}

    public Long getSeller() {
        return seller;
    }

    public void setSeller(Long seller) {
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

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

}
