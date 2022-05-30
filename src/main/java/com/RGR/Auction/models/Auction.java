package com.RGR.Auction.models;

import java.util.Collection;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name="auction")
public class Auction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	@ManyToOne 
	@JoinColumn(name="ID_product")
	private Lot lot;
	@Column(name="ID_comment")
	@OneToMany(fetch=FetchType.EAGER)
	private Collection <Comment> comments;
	@Column(name="Cost")
	private int cost;
	@Column(name="Start")
	private Date start;
	@Column(name="End")
	private Date end;
	
	
	public Auction() {

	}
	public Auction( Lot lot, Collection<Comment> comments, int cost, Date start, Date end) {
		this.lot = lot;
		this.comments = comments;
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
	
	public Lot getLot() {
		return lot;
	}
	public void setLot(Lot lot) {
		this.lot = lot;
	}
	
	public Collection<Comment> getComments() {
		return comments;
	}
	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}
	public int getCost() {
		return cost;
	}
	public void setCoast(int cost) {
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
