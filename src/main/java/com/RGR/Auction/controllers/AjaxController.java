package com.RGR.Auction.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RGR.Auction.Service.Auction.AuctionService;
import com.RGR.Auction.Service.Lot.LotService;
import com.RGR.Auction.models.Auction;
import com.RGR.Auction.models.AuctionLot;
import com.RGR.Auction.models.Data;
import com.RGR.Auction.models.Lot;
import com.RGR.Auction.repositories.LotRepositories;


@RestController
@RequestMapping("/ajax")
public class AjaxController {
	
	@Autowired
	LotRepositories lotRepository;
    @Autowired
    LotService lotService;    
    @Autowired
    AuctionService auctionService;    

    
    @GetMapping("/get_lots")
    public List<Lot> getLots(@AuthenticationPrincipal Data user) {
        return lotRepository.findBySeller(user.getId());
    }
    @GetMapping("/get_all_lots")
    public List<Lot> getAllLots() {
        return lotService.getAll();
    }
    @SuppressWarnings("null")
	@GetMapping("/show_auctions")
    public List<Auction> showAuctions(@AuthenticationPrincipal Data user) {
    	/*
    	List <Auction> listResult = null;
    	
    	List<Auction> listAuction=auctionService.getAll();
    	List<Lot>lots=lotRepository.findBySeller(user.getId());
    	List<Integer> listIdLots = lots.stream().map(Lot::getId).collect(Collectors.toList());
    	List<Integer> listLotsFromAuction = listAuction.stream().map(Auction::getLot).collect(Collectors.toList());
    	
    	listLotsFromAuction.retainAll(listIdLots);
    	
    	for (int i=0; i<listLotsFromAuction.size();i++) {
    		listResult.add((Auction) auctionService.getByLot(listLotsFromAuction.get(i)));
    	}
    	
        return listResult;
        */
    	return auctionService.getAll();
    }
    
    @PostMapping("/add_lot")
    public void addLot(@RequestBody Lot lot) {
    	lotService.saveLot(lot);
    }
    @PostMapping("/add_auction")
    public void addAuction(@RequestBody Auction auction) {
    	auctionService.saveAuction(auction);
    }
    
}

