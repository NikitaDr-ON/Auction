package com.RGR.Auction.Service.PurchaseSale;

import java.util.List;

import com.RGR.Auction.models.Data;
import com.RGR.Auction.models.Product;
import org.springframework.stereotype.Service;
import com.RGR.Auction.models.PurchaseSale;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface PurchaseSaleService {
    PurchaseSale getPurchaseSaleById(int id);
    List<PurchaseSale> getAllPurchaseSale();
    @Transactional
    List<PurchaseSale> getAllPurchaseSaleByBuyer(Data user);

    @Transactional
    void addPurchaseSale(Data user, Product product, int purchaseQuant);

    void deletePurchaseSale(int idSale);

    @Transactional
    void checkOldPurchaseSale(int idSale);
}
