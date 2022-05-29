package com.RGR.Auction.Service.Lot;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.RGR.Auction.models.CategoryModel;
import com.RGR.Auction.models.Data;
import com.RGR.Auction.models.Lot;

@Service
public interface LotService {

	List<Lot> getAll();
	Lot getById(long id) throws NotFoundException;
	void saveLot(String product, int startCost, Data seller, String description, String photo, CategoryModel category);
	boolean deleteById(long id);
	Lot update(long id, Lot lot) throws NotFoundException;
	//boolean deleteByNameProduct(String nameProduct);
	
}