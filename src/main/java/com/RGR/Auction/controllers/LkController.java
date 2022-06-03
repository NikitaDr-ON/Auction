package com.RGR.Auction.controllers;

import com.RGR.Auction.Service.Lot.LotServiceImpl;
import com.RGR.Auction.models.Lot;
import com.RGR.Auction.repositories.CategoryRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.RGR.Auction.Service.Lot.LotService;
import com.RGR.Auction.models.Data;
import com.RGR.Auction.repositories.LotRepositories;
import com.RGR.Auction.repositories.Repositories;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/lk")
public class LkController {
	
	@Autowired
	LotRepositories lotRepository;
	@Autowired
 	private CategoryRepositories catRepository;
	@Autowired
 	private Repositories userRepository;
	@Autowired
	LotServiceImpl lotServiceImpl;
 
	@GetMapping()
	public String getAllLots(Model model) {

	// Iterable<Lot> lots=lotRepository.findAll();
    // model.addAttribute("lots",lots);
		return "lk";
	}
	
	
	/*
	@PostMapping()
	public String getAllLots(@RequestParam String product, @RequestParam int startCost, @RequestParam String description,
							 @RequestParam("file") MultipartFile file, @RequestParam Long categoryId, Model model, @AuthenticationPrincipal Data user)  throws IOException{
		lotServiceImpl.saveLot(product,startCost,user.getId(),description,file,categoryId);
		List<Lot> lots=lotRepository.findBySeller(79L);
		System.out.println(user.getId());
		model.addAttribute("lots",lots);
		return "lk";
	}
	*/
	

}
