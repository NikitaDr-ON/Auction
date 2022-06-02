package com.RGR.Auction.Service.Lot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.RGR.Auction.models.CategoryModel;
import com.RGR.Auction.models.Data;
import com.RGR.Auction.models.Lot;
import com.RGR.Auction.repositories.LotRepositories;

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
    @Override
    public void saveLot(String product, int startCost, Data seller, String description, String photo, CategoryModel category) {
    	   
       Lot newLot = new Lot(product,startCost,seller,description,photo,category);
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
    

	public boolean deleteByNameProduct(String nameProduct) {
    	boolean flag=false;
    	Iterable<Lot> lots = lotRepository.findAll();
    	
    	for (int i = 0; i < ((List<Lot>) lots).size(); i++) {
    	    Lot lot=((List<Lot>) lots).get(i);
    	    if(lot.getProduct()==nameProduct) { 
    	    	int lotid=lot.getId();
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

    

}
