package com.RGR.Auction.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.RGR.Auction.repositories.Repositories;
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
import com.RGR.Auction.repositories.AuctionRepositories;
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
    @Autowired
    AuctionRepositories auctionRepository;
    @Autowired
    Repositories repositories;

    
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
    public List<AuctionLot> showAuctions(@AuthenticationPrincipal Data user) {
    	List<AuctionLot> al = new ArrayList<>();
    	
    	List<Auction> auctions = (List<Auction>) auctionRepository.findAll();
    	for(int i=0;i<auctions.size();i++) {
    		al.add(new AuctionLot((auctions).get(i).getId(),lotRepository.findById(auctions.get(i).getLot()), (auctions).get(i).getStart(),(auctions).get(i).getEnd(),(auctions).get(i).getCost()));	
    	} 
    	return al;
    }
    
    @PostMapping("/add_lot")
    public void addLot(@RequestBody Lot lot) {
    	lotService.saveLot(lot);
    }

    @PostMapping("/add_auction")
    public void addAuction(@RequestBody Auction auction) {
    	auction.setCost(lotRepository.findById(auction.getLot()).getStartCost());
    	auctionService.saveAuction(auction);
    }

    @PostMapping("/take_rate")
    public void takeRate(@RequestBody Auction auction) {
    	int rate=auction.getCost();
    	List<Auction> upAuction=auctionRepository.findByLot(auction.getLot());
    	Auction updateAuction=upAuction.get(0);
    	System.out.println(rate+"id"+updateAuction.getId());
    	if( rate>=updateAuction.getCost()) {
    	updateAuction.setCost(rate);
    	}
    	auctionRepository.save(updateAuction);
    }

    @GetMapping("/get_vintage")
    public List<Lot> getVintageLots() {
        List<Lot> lots = lotRepository.findAll();
        List<Lot> vintage = new ArrayList<>();
        for(int i=0;i< lots.size();i++)
        {
            if(lots.get(i).getCategory()==1)
                vintage.add(lots.get(i));
        }
        return vintage;
    }
    @GetMapping("/get_antic")
    public List<Lot> getAnticLots() {
        List<Lot> lots = lotRepository.findAll();
        List<Lot> antic = new ArrayList<>();
        for(int i=0;i< lots.size();i++)
        {
            if(lots.get(i).getCategory()==2)
                antic.add(lots.get(i));
        }
        return antic;
    }
    @GetMapping("/get_handmade")
    public List<Lot> getHandmadeLots(){
        List<Lot> lots = lotRepository.findAll();
        List<Lot> handmade = new ArrayList<>();
        for(int i=0;i< lots.size();i++)
        {
            if(lots.get(i).getCategory()==3)
                handmade.add(lots.get(i));
        }
        return handmade;
    }

    @GetMapping("/get_collectable")
    public List<Lot> getCollectableLots() {
        List<Lot> lots = lotRepository.findAll();
        List<Lot> collectable = new ArrayList<>();
        for(int i=0;i< lots.size();i++)
        {
            if(lots.get(i).getCategory()==4)
                collectable.add(lots.get(i));
        }
        return collectable;
    }

    @GetMapping("/get_drag")
    public List<Lot> getDragLots() {
        List<Lot> lots = lotRepository.findAll();
        List<Lot> jewelry = new ArrayList<>();
        for(int i=0;i< lots.size();i++)
        {
            if(lots.get(i).getCategory()==5)
                jewelry.add(lots.get(i));
        }
        return jewelry;
    }
    @GetMapping("/get_user")
    public Data getUser(@AuthenticationPrincipal Data usersup) {
        Data user = repositories.findById(usersup.getId());
        System.out.println(user.getId());
        return user;
    }
}

