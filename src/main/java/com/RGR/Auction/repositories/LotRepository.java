package com.RGR.Auction.repositories;

import com.RGR.Auction.models.Lot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotRepository extends CrudRepository<Lot,Long> {
}
