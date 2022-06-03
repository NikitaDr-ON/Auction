package com.RGR.Auction.Service.Auction;

import java.util.Date;
import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.RGR.Auction.models.Auction;

public interface AuctionService {
	
	List<Auction> getAll();
	Auction getById(int id) throws NotFoundException;
	void saveAuction(int lot_id, int cost, Date start, Date end);
	boolean deleteById(int id);
	//Auction update(int id, Auction auction) throws NotFoundException;	
	
}
