package com.RGR.Auction.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="purchase_sale")
public class PurchaseSale {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_sale")
	private int id;
	@ManyToOne 
	@JoinColumn(name="Buyer")
	private Data buyer;
	@ManyToOne 
	@JoinColumn(name="Seller")
	private Data seller;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Data getBuyer() {
		return buyer;
	}
	public void setBuyer(Data buyer) {
		this.buyer = buyer;
	}
	public Data getSeller() {
		return seller;
	}
	public void setSeller(Data seller) {
		this.seller = seller;
	}
	
	
	
	

}
