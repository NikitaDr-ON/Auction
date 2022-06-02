package com.RGR.Auction.Service.Lot;

import java.awt.*;
import java.io.IOException;
import java.sql.Blob;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.RGR.Auction.models.CategoryModel;
import com.RGR.Auction.models.Data;
import com.RGR.Auction.models.Lot;
import com.RGR.Auction.repositories.LotRepositories;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class LotServiceImpl implements LotService {

    @Autowired
    private LotRepositories lotRepository;

    @Override
    public List<Lot> getAll() {
        return lotRepository.findAllByOrderByIdDesc();
    }

    @Override
    public Lot getById(int id) throws NotFoundException {
        Lot lot = lotRepository.findById(id);
        if (lot == null) {
            throw new NotFoundException();
        }
        return lot;
    }
   /* @Override
    public void saveLot(String product, int startCost, Data seller, String description, Blob photo, CategoryModel category) {
    	   
       Lot newLot = new Lot(product,startCost,seller,description,photo,category);
       lotRepository.save(newLot);
    }*/

    @Override
    public void saveLot(String product, int startCost, Long seller, String description, MultipartFile file, Long category) throws IOException {
       // Lot newLot = new Lot(product, startCost, seller, description, category);
        Lot newLot = new Lot();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            newLot.setPhoto(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        newLot.setProduct(product);
        newLot.setStartCost(startCost);
        newLot.setSeller(seller);
        newLot.setDescription(description);
        newLot.setCategory(category);
        lotRepository.save(newLot);
    }


    @Override
    public boolean deleteById(int id) {
    	Lot lot = lotRepository.findById(id);
        if (lot == null) {
            return false;
        }
        else {
        	lotRepository.deleteById(id);
        	return true;
        }              
    }
    
    @Override
    public Lot update(int id, Lot lot) throws NotFoundException {
        if (!lotRepository.existsById(id)) {
            throw new NotFoundException();
        }
        Lot newLot = new Lot();
        newLot.setId(id);
        newLot.setProduct(lot.getProduct());
        newLot.setStartCost(lot.getStartCost());
        newLot.setDescription(lot.getDescription());
        newLot.setCategory(lot.getCategory());
        newLot.setPhoto(lot.getPhoto());
        newLot.setSeller(lot.getSeller());
        return lotRepository.save(newLot);
    }
    
/*
	public boolean deleteByNameProduct(String nameProduct) {
    	boolean flag=false;
    	Iterable<Lot> lots = lotRepository.findAll();
    	
    	for (int i = 0; i < ((List<Lot>) lots).size(); i++) {
    	    Lot lot=((List<Lot>) lots).get(i);
    	    if(lot.getProduct()==nameProduct) { 
    	    	Long lotid=lot.getId();
    	    	lotRepository.deleteById(lotid);
    	    	flag=true;
    	    }
    	}
    	
        if (flag == false) {
            return false;
        }
        else {
        	return true;
        }      
    }

    */

}
