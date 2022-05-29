package com.RGR.Auction.repositories;

import com.RGR.Auction.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
}