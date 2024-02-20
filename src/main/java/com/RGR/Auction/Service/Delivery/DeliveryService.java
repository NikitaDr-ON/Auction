package com.RGR.Auction.Service.Delivery;

import java.util.List;

import com.RGR.Auction.models.Delivery;
import com.RGR.Auction.models.User;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
public interface DeliveryService {

	@Transactional
	void addDelivery(int id_sale, int service);

    @Transactional
    void payDelivery(User user);

    @Transactional
    List<Delivery> getDeliveriesByUser(User user);

    @Transactional
    List<Delivery> getActiveDeliveriesByUser(User user);

    @Transactional
    List<Delivery> deleteActiveDeliveriesByUser(User user);

    @Transactional
    List<Delivery> getAllDeliveries();
}
