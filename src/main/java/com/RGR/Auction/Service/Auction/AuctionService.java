package com.RGR.Auction.Service.Auction;

import java.util.Date;
import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.RGR.Auction.models.Auction;

@Service
public interface AuctionService {
	
	List<Auction> getAll();
	Auction getById(int id) throws NotFoundException;
	void saveAuction(int lot_id, int cost, Date start, Date end);
	boolean deleteById(int id);
	//Auction update(int id, Auction auction) throws NotFoundException;	
	void saveAuction(Auction auction);
	boolean findByIdseller(int id);
	List<Auction> getByLot(int id_lot);
	
}
