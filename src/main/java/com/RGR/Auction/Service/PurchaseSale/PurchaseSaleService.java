package com.RGR.Auction.Service.PurchaseSale;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.RGR.Auction.models.PurchaseSale;
@Service
public interface PurchaseSaleService {

	List<PurchaseSale> getAll();

	void savePurchaseSale(int id_Seller, int id_Buyer) throws NotFoundException;

	PurchaseSale getById(int id) throws NotFoundException;
	
}
