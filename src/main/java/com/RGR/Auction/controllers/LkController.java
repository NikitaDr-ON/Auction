package com.RGR.Auction.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;

import javax.persistence.EntityManager;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.RGR.Auction.Service.Lot.LotService;
import com.RGR.Auction.models.CategoryModel;
import com.RGR.Auction.models.Data;
import com.RGR.Auction.models.Lot;
import com.RGR.Auction.repositories.CategoryRepositories;
import com.RGR.Auction.repositories.LotRepositories;
import com.RGR.Auction.repositories.Repositories;

public class LkController {
	
	@Autowired
	LotRepositories lotRepository;
	@Autowired
    LotService lotService;
	@Autowired
 	private CategoryRepositories catRepository;
	@Autowired
 	private Repositories userRepository;
 
	@GetMapping("/lk")
    public String getAllLots(Model model) {
	 
	 Iterable <Lot> lots=lotRepository.findAll();
	 
     model.addAttribute("lots",lots);
    return "lk";
	}

	@SuppressWarnings("null") 
	@PostMapping("lk")
    public String add(@RequestParam String product, @RequestParam int startCost, @RequestParam int sellerId, @RequestParam String description,
    		 @RequestParam MultipartFile photo, @RequestParam int categoryId, Model model) {
		InputStream iStream = null;
		try {
			iStream = photo.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    long size = photo.getSize();
	    EntityManager emf = null;
		Session session = emf.unwrap(Session.class);
	    Blob blobPhoto = Hibernate.getLobCreator(session).createBlob(iStream, size);
	    
	
    CategoryModel category=new CategoryModel();
    category=catRepository.findById(categoryId);
    
    Data seller=new Data();	        
    seller=userRepository.findById(sellerId);
    
    lotService.saveLot(product,startCost,seller,description,blobPhoto,category);	        
        Iterable<Lot> lots = lotRepository.findAll();
        model.addAttribute("lots", lots);
        return "lk";
    }




}
