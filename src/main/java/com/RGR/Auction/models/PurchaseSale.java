package com.RGR.Auction.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


public class PurchaseSale {
	private int id_sale;
	private int buyer;
	private int product_id;
	private int purchase_price;
	private int purchase_quant;

	public int getId() {
		return id_sale;
	}
	public void setId(int id_sale) {
		this.id_sale = id_sale;
	}
	public int getBuyer() {
		return buyer;
	}
	public void setBuyer(int buyer) {
		this.buyer = buyer;
	}

	public int getPurchase_price() {
		return purchase_price;
	}

	public void setPurchase_price(int purchase_price) {
		this.purchase_price = purchase_price;
	}

	public int getPurchase_quant() {
		return purchase_quant;
	}

	public void setPurchase_quant(int purchase_quant) {
		this.purchase_quant = purchase_quant;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getId_sale() {
		return id_sale;
	}

	public void setId_sale(int id_sale) {
		this.id_sale = id_sale;
	}
}
