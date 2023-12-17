package com.RGR.Auction.Service.Delivery;

import java.util.List;

import com.RGR.Auction.models.Data;
import com.RGR.Auction.models.Delivery;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
public interface DeliveryService {

	@Transactional
	void addDelivery(int id_sale, int service);

    @Transactional
    void payDelivery(Data user);

    @Transactional
    List<Delivery> getDeliveriesByUser(Data user);

    @Transactional
    List<Delivery> getActiveDeliveriesByUser(Data user);

    @Transactional
    List<Delivery> deleteActiveDeliveriesByUser(Data user);
}
