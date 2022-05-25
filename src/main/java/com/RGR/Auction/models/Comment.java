package com.RGR.Auction.models;

import javax.persistence.*;

@Entity
@Table(name="comments")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="Text")
	private String text;
	@ManyToOne   
    @JoinColumn(name="Support_id")
	private Data support_id;
	
	
	public Comment() {
		
	}
	public Comment(int id, String text, Data support_id) {
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
	public Data getSupport_id() {
		return support_id;
	}
	public void setSupport_id(Data support_id) {
		this.support_id = support_id;
	}

	
}
