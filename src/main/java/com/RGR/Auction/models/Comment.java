package com.RGR.Auction.models;

import javax.persistence.*;

@Entity
@Table(name="comments")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_comment")
	private int id;
	@Column(name="Text")
	private String text;
	@Column(name="Support_id")
	private int support_id;
	
	private int auction;
	
	
	public Comment() {
		
	}
	public Comment(int id, String text, int support_id) {
		this.id = id;
		this.text = text;
		this.support_id = support_id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getSupport_id() {
		return support_id;
	}
	public void setSupport_id(int support_id) {
		this.support_id = support_id;
	}
	
	public int getAuction() {
		return auction;
	}
	public void setAuction(int auction) {
		this.auction = auction;
	}
	
}
