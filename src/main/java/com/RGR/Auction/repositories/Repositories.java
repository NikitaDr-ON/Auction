package com.RGR.Auction.repositories;

import com.RGR.Auction.models.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface Repositories extends CrudRepository<Data, Long> {
    /*
    @Query(value = "SELECT ID,name,surname,fathername,birthdate,gender,phone,mail,address,cardInfo,password FROM users " +
            "WHERE name=:username", nativeQuery = true)
    Data findByName(@Param("username") String name);
    @Query(value = "SELECT ID,name,surname,fathername,birthdate,gender,phone,mail,address,cardinfo,Password FROM users" +
            " WHERE mail LIKE %:mail% ", nativeQuery = true)
    Data findByMail(@Param("mail") String mail);

     */
    Data findByName(String name);
    Data findByMail(String mail);
    Data findById(long id);

    Data findByActivationCode(String code);
}
