package com.RGR.Auction.repositories;

import com.RGR.Auction.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository  extends CrudRepository<User, Long> {

    @Query(value = "SELECT * FROM users" +
            " WHERE mail = ?1 ", nativeQuery = true)
    User findByMail(@Param("mail") String mail);

    @Query(value = "SELECT * FROM users " +
            "WHERE Activation_code= ?1", nativeQuery = true)
    User findByActivationCode(@Param("Activation_code") String code);

    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<User> findAll();
    @Query(value = "SELECT * FROM users " +
            "WHERE ID= ?1", nativeQuery = true)
    User findById(@Param("ID") long id);
    @Query(value = "SELECT * FROM users " +
            "WHERE name= ?1", nativeQuery = true)
    User findByName(@Param("name") String name);


}
