package com.RGR.Auction.models;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="auction_tbl")
public class Auction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	@Column(name="ID_product")
	private int lot;
	@Column(name="Cost")
	private int cost;
	@Column(name="Start")
	private Date start;
	@Column(name="End")
	private Date end;
	//private Collection <Comment> comments;
	
	
	public Auction() {

	}
	public Auction( int lot, int cost, Date start, Date end) {
		this.lot = lot;
		this.cost = cost;
		this.start = start;
		this.end = end;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getLot() {
		return lot;
	}
	public void setLot(int lot) {
		this.lot = lot;
	}
	/*
	public Collection<Comment> getComments() {
		return comments;
	}
	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}
	*/
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
		
}
