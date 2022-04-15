package com.RGR.Auction.repositories;

import com.RGR.Auction.models.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repositories extends CrudRepository<Data, Long> {

}
