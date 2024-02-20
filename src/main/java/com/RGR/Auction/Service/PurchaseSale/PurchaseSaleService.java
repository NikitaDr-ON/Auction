package com.RGR.Auction.Service.PurchaseSale;

import java.util.List;

import com.RGR.Auction.models.Product;
import com.RGR.Auction.models.User;
import org.springframework.stereotype.Service;
import com.RGR.Auction.models.PurchaseSale;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface PurchaseSaleService {
    PurchaseSale getPurchaseSaleById(int id);
    List<PurchaseSale> getAllPurchaseSaleWithOld();
    List<PurchaseSale> getAllPurchaseSaleByBuyer(User user);
    void addPurchaseSale(User user, Product product, int purchaseQuant);

    @Transactional
    List<PurchaseSale> getAllPurchaseSaleWithOldByUser(User user);

    @Transactional
    List<PurchaseSale> getAllPurchaseSale();

    void deletePurchaseSale(int idSale);
    void checkOldPurchaseSale(int idSale);
}
