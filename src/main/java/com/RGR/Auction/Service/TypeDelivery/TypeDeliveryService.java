package com.RGR.Auction.Service.TypeDelivery;

import com.RGR.Auction.models.ServiceModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface TypeDeliveryService {
    @Transactional
    ServiceModel getServiceById(int id);

    @Transactional
    List<ServiceModel> getAllServices();
}
