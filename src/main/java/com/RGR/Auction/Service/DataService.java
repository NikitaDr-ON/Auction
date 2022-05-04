package com.RGR.Auction.Service;

import com.RGR.Auction.models.Data;
import com.RGR.Auction.repositories.Repositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DataService implements UserDetailsService {

   @Autowired
   Repositories Repository;
   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      Data user = Repository.findByName(username);

      if (user == null) {
         throw new UsernameNotFoundException("User not found");
      }

      return user;
   }

   public Data findUserById(Long userId) {
      Optional<Data> userFromDb = Repository.findById(userId);
      return userFromDb.orElse(new Data());
   }



}
