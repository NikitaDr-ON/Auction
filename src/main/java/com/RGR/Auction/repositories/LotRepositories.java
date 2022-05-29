package com.RGR.Auction.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.RGR.Auction.models.CategoryModel;
import com.RGR.Auction.models.Data;
import com.RGR.Auction.models.Lot;

@Repository
public interface LotRepositories extends CrudRepository<Lot, Long> {
	
    Lot findById(long id);
    List <Lot> findAllByOrderByIdDesc();
	
}