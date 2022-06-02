package com.RGR.Auction.models;

import java.sql.Blob;

import javax.persistence.*;


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
  //  @ManyToOne
   // @JoinColumn(name="Seller")
    //private Data seller;
    private Long seller;
    @Column(name="Description")
    private String description;
    @Lob
    @Column(name="Photo" ,columnDefinition = "MEDIUMBLOB")
    private byte[] photo;
    //private Blob photo;
	//@ManyToOne
   // @JoinColumn(name="Category")
   //private CategoryModel category;
    private Long category;
    

   /* public Lot(String product, int startCost, Data seller, String description, Blob photo, CategoryModel category) {
		
		this.product = product;
		this.startCost = startCost;
		this.seller = seller;
		this.description = description;
		this.photo = photo;
		this.category = category;
	}*/

    public Lot(String product, int startCost, Long seller, String description, byte[] photo, Long category) {
        this.product = product;
        this.startCost = startCost;
        this.seller = seller;
        this.description = description;
        this.photo = photo;
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

    public int getStartCost() {
		return startCost;
	}

	public void setStartCost(int startCost) {
		this.startCost = startCost;
	}

	/*public Data getSeller() {
        return seller;
    }*/

   /* public void setSeller(Data seller) {
        this.seller = seller;
    }*/

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
   /* public Blob getPhoto() {
		return photo;
	}

	public void setPhoto(Blob photo) {
		this.photo = photo;
	}*/

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    /* public CategoryModel getCategory() {
        return category;
    }

    public void setCategory( CategoryModel category) {
        this.category = category;
    }*/

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }
}
