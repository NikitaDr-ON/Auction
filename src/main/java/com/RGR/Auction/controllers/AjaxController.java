package com.RGR.Auction.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RGR.Auction.Service.Lot.LotService;
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

    @GetMapping("/get_lots")
    public List<Lot> getLots(@AuthenticationPrincipal Data user) {
        return lotRepository.findBySeller(user.getId());
    }
    @GetMapping("/get_all_lots")
    public List<Lot> getAllLots() {
        return lotService.getAll();
    }
    @PostMapping("/add_lot")
    public void addLot(@RequestBody Lot lot) {
    	lotService.saveLot(lot);
    }
}

