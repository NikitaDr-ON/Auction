package com.RGR.Auction.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.RGR.Auction.models.Auction;

public interface AuctionRepositories extends CrudRepository <Auction,Integer> {
	
	Auction findById(long id);
    List <Auction> findAllByOrderByIdDesc();
	List<Auction> findByLot(int id_lot);
	void deleteById(long id);

}
