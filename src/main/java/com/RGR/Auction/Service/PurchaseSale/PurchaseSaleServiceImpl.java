package com.RGR.Auction.Service.PurchaseSale;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.RGR.Auction.models.Data;
import com.RGR.Auction.models.PurchaseSale;
import com.RGR.Auction.repositories.PurchaseSaleRepositories;
import com.RGR.Auction.repositories.Repositories;

@Service
public class PurchaseSaleServiceImpl implements PurchaseSaleService{
	
	@Autowired
    private PurchaseSaleRepositories purchaseSaleRepository;
	@Autowired
    private Repositories userRepository;

    @Override
    public List<PurchaseSale> getAll() {
        return purchaseSaleRepository.findAllByOrderByIdDesc();
    }

    @Override
    public PurchaseSale getById(int id) throws NotFoundException {
    	PurchaseSale purchaseSale = purchaseSaleRepository.findById(id);
        if (purchaseSale == null) {
            throw new NotFoundException();
        }
        return purchaseSale;
    }

    @Override
    public void savePurchaseSale(int id_Seller, int id_Buyer) throws NotFoundException {
    	PurchaseSale newPurchaseSale = new PurchaseSale();
    	
    	Data seller=userRepository.findById(id_Seller);
    	Data buyer=userRepository.findById(id_Buyer);
    	if (seller == null || buyer==null) {
            throw new NotFoundException();
        }
    	newPurchaseSale.setSeller(seller);
    	newPurchaseSale.setBuyer(buyer);
        
    	purchaseSaleRepository.save(newPurchaseSale);
    }
    

}
