package com.RGR.Auction.Service.Auction;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.RGR.Auction.models.Auction;
import com.RGR.Auction.models.Comment;
import com.RGR.Auction.models.Lot;

public interface AuctionService {
	
	List<Auction> getAll();
	Auction getById(int id) throws NotFoundException;
	void saveAuction(Lot lot, Collection<Comment> comments, int cost, Date start, Date end);
	boolean deleteById(int id);
	//Auction update(int id, Auction auction) throws NotFoundException;	
	
}
