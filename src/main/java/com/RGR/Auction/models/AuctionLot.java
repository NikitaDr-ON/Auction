package com.RGR.Auction.models;

import java.util.Date;

public class AuctionLot {

	private int id;
	private Lot lot;
	private Date start;
	private Date end;
	private int cost;
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
	public AuctionLot(int id, Lot lot, Date start, Date end,int cost) {

		this.id = id;
		this.lot = lot;
		this.start = start;
		this.end = end;
		this.cost=cost;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public AuctionLot() {

	}
	
	
	
	

}
