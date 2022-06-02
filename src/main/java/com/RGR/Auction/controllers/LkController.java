package com.RGR.Auction.controllers;

import com.RGR.Auction.Service.Lot.LotServiceImpl;
import com.RGR.Auction.models.Lot;
import com.RGR.Auction.repositories.CategoryRepositories;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.RGR.Auction.Service.Lot.LotService;
import com.RGR.Auction.models.CategoryModel;
import com.RGR.Auction.models.Data;
import com.RGR.Auction.repositories.LotRepositories;
import com.RGR.Auction.repositories.Repositories;

import javax.mail.Session;
import javax.persistence.EntityManager;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;
import java.util.Optional;
@Controller
public class LkController {
	
	@Autowired
	LotRepositories lotRepository;
	@Autowired
    LotService lotService;
	@Autowired
 	private CategoryRepositories catRepository;
	@Autowired
 	private Repositories userRepository;
	@Autowired
	LotServiceImpl lotServiceImpl;
 
	@GetMapping("/lk")
	public String getAllLots(Model model,@AuthenticationPrincipal Data user) {

	 Iterable<Lot> lots=lotRepository.findAll();
     model.addAttribute("lots",lots);
		return "lk";
	}
	@PostMapping("/lk")
	public String getAllLots(@RequestParam String product, @RequestParam int startCost, @RequestParam String description,
							 @RequestParam("file") MultipartFile file, @RequestParam Long categoryId, Model model, @AuthenticationPrincipal Data user)  throws IOException{
		lotServiceImpl.saveLot(product,startCost,user.getId(),description,file,categoryId);
		List<Lot> lots=lotRepository.findBySeller(79L);
		System.out.println(user.getId());
		model.addAttribute("lots",lots);
		return "lk";
	}
	/*@SuppressWarnings("null")

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
    		 @RequestParam String photo, @RequestParam int categoryId, Model model) {
       CategoryModel category=new CategoryModel();
	   category=catRepository.findById(categoryId);
        
        Data seller=new Data();	        
        seller=userRepository.findById(sellerId);
        
        lotService.saveLot(product,startCost,seller,description,photo,category);
        	        
    		 @RequestParam String photo, @RequestParam int categoryId, Model model) {
       CategoryModel category=new CategoryModel();
	   category=catRepository.findById(categoryId);
        
        Data seller=new Data();	        
        seller=userRepository.findById(sellerId);
        
        lotService.saveLot(product,startCost,seller,description,photo,category);
        	        
    		 @RequestParam String photo, @RequestParam int categoryId, Model model) {
       CategoryModel category=new CategoryModel();
	   category=catRepository.findById(categoryId);
        
        Data seller=new Data();	        
        seller=userRepository.findById(sellerId);
        
        lotService.saveLot(product,startCost,seller,description,photo,category);
        	        
        Iterable<Lot> lots = lotRepository.findAll();
        model.addAttribute("lots", lots);
        return "lk";
    }*/




}
