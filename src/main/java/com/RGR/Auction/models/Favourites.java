package com.RGR.Auction.models;

public class Favourites {
	
	private int id_lot;
	private long id_user;
	
	public Favourites(int id_lot, long id_user) {
		this.id_lot = id_lot;
		this.id_user = id_user;
	}
	
	public String toString() {
		return "lot:"+id_lot+"  user:"+id_user;		
	}
	
	
}
