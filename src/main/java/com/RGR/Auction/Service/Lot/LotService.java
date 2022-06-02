package com.RGR.Auction.Service.Lot;

import java.io.IOException;
import java.sql.Blob;
import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.RGR.Auction.models.CategoryModel;
import com.RGR.Auction.models.Data;
import com.RGR.Auction.models.Lot;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface LotService {

	List<Lot> getAll();
	Lot getById(int id) throws NotFoundException;
	//void saveLot(String product, int startCost, Data seller, String description, Blob photo, CategoryModel category);
	void saveLot(String product, int startCost, Long seller, String description, MultipartFile file, Long category) throws IOException;
	boolean deleteById(int id);
	Lot update(int id, Lot lot) throws NotFoundException;
	//boolean deleteByNameProduct(String nameProduct);
	
}