package com.RGR.Auction.repositories;

import com.RGR.Auction.models.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repositories extends CrudRepository<Data, Long> {
    Data findByName(String name);
    Data findByMail(String mail);
    Data findById(long id);

    Data findByActivationCode(String code);
}
