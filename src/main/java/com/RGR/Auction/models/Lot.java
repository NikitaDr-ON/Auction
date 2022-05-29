package com.RGR.Auction.models;

import javax.persistence.*;

@Entity
@Table(name="lot")
public class Lot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_Product")
    private long id;
    @Column(name="Product_name")
    private String product;
    @Column(name="StartCost")
    private int startCost;
    @ManyToOne   
    @JoinColumn(name="Seller")  
    private Data seller;
    @Column(name="Description")
    private String description;
    @Column(name="Photo")
    private String photo;
	@ManyToOne   
    @JoinColumn(name="Category")  
    private CategoryModel category;
    

    public Lot(String product, int startCost, Data seller, String description, String photo, CategoryModel category) {
		
		this.product = product;
		this.startCost = startCost;
		this.seller = seller;
		this.description = description;
		this.photo = photo;
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

    public int getStartCost() {
		return startCost;
	}

	public void setStartCost(int startCost) {
		this.startCost = startCost;
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
    public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory( CategoryModel category) {
        this.category = category;
    }
}
