package com.RGR.Auction.Service.Seller;

import com.RGR.Auction.models.Seller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface SellerService {
    Seller getSellerById(int id);
    List<Seller> getAllSeller();
}
