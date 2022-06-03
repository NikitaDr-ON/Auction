package com.RGR.Auction.Service.Auction;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.RGR.Auction.models.Auction;
import com.RGR.Auction.repositories.AuctionRepositories;

public class AuctionServiceImpl implements AuctionService {

	@Autowired
	private AuctionRepositories auctionRepository;
	
	@Override
	public List<Auction> getAll() {
		return auctionRepository.findAllByOrderByIdDesc();
	}

	@Override
	public Auction getById(int id) throws NotFoundException {
		Auction auction = auctionRepository.findById(id);
	     if (auction == null) {
	         throw new NotFoundException();
	         }
	     return auction;
	}	
	
	@Override
    public void saveAuction(int lot_id, int cost, Date start, Date end) {
    	   
       Auction newAuction = new Auction(lot_id,cost,start,end);
       auctionRepository.save(newAuction);
    }
    
    @Override
    public boolean deleteById(int id) {
    	Auction auction = auctionRepository.findById(id);
        if (auction == null) {
            return false;
        }
        else {
        	auctionRepository.deleteById(id);
        	return true;
        }              
    }
    /*
    @Override
    public Auction update(int id, Auction auction) throws NotFoundException {
        if (!auctionRepository.existsById(id)) {
            throw new NotFoundException();
        }
        Auction newAuction = new Auction();
        newAuction.setId(id);
        newAuction.setLot(auction.getLot());
        newAuction.setComments(auction.getComments());
        newAuction.setCost(auction.getCost());
        newAuction.setStart(auction.getStart());
        newAuction.setEnd(auction.getEnd());
        
        return auctionRepository.save(newAuction);
    }
    */

}
