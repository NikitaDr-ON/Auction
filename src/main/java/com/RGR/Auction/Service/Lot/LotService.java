package com.RGR.Auction.Service.Lot;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.RGR.Auction.models.Lot;

@Service
public interface LotService {

	List<Lot> getAll();
	Lot getById(Long id) throws NotFoundException;
 
}