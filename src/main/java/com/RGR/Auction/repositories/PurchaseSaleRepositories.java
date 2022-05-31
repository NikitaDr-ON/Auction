package com.RGR.Auction.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.RGR.Auction.models.PurchaseSale;

public interface PurchaseSaleRepositories extends CrudRepository<PurchaseSale, Integer>{

	PurchaseSale findById(int id);
	List <PurchaseSale> findAllByOrderByIdDesc();
}
