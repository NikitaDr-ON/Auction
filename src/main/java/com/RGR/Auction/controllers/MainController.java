package com.RGR.Auction.controllers;

import com.RGR.Auction.models.Auction;
import com.RGR.Auction.models.AuctionLot;
import com.RGR.Auction.models.Data;
import com.RGR.Auction.models.Favourites;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.RGR.Auction.models.Lot;
import com.RGR.Auction.repositories.AuctionRepositories;
import com.RGR.Auction.repositories.LotRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

	@Autowired
    LotRepositories lotRepositories;
	@Autowired
	AuctionRepositories auctionRepository;


   /* @GetMapping("/hello")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }*/

    @PostMapping("/antikiick")
    public String show(@RequestParam String name,@RequestParam String surname,@RequestParam String mail,@RequestParam String password,Model model)
    {
        return "antikiick";
    }
    
    @SuppressWarnings("null")
	@GetMapping("/index")
    public String showIndex( Model model) {
    	List<AuctionLot> al = new ArrayList<>();
    	
    	List<Auction> auctions = (List<Auction>) auctionRepository.findAll();
    	for(int i=0;i<auctions.size();i++) {
    		al.add(new AuctionLot((auctions).get(i).getId(),lotRepositories.findById(auctions.get(i).getLot()), (auctions).get(i).getStart(),(auctions).get(i).getEnd(),(auctions).get(i).getCost()));	
    	} 
        model.addAttribute("auctionLot",al);
        return "index";
    }
    
    @GetMapping("/index/id={id}")
    public String takeRate(@PathVariable("id") int id, @AuthenticationPrincipal Data user, Model model) {
    	model.addAttribute("lots", lotRepositories.findById(id));
    	model.addAttribute("id_product",id);
        return "rate";
    }
    
    @PostMapping("/index/id={id}")
    public String saveRate(@RequestParam int id_product ,@RequestParam int rate,Model model)
    {
    	boolean status=false; 
    	List<Auction> upAuction=auctionRepository.findByLot(id_product);
    	Auction updateAuction=upAuction.get(0);
    	if( rate>=updateAuction.getCost()) {
    	updateAuction.setCost(rate);
    	status=true;    	
    	}
    	if(auctionRepository.save(updateAuction) != null && status==true)
    	model.addAttribute("result", "Ваша ставка зарегистрирована!");
    	else model.addAttribute("result", "Ваша ставка не зарегистрирована!");
    	return "rate";
    }
    
  
    


}