package com.RGR.Auction.controllers;

import java.io.IOException;
import java.util.ArrayList;
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
}

