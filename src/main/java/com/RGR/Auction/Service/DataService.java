package com.RGR.Auction.Service;

import com.RGR.Auction.Service.Lot.LotService;
import com.RGR.Auction.models.Data;
import com.RGR.Auction.models.Lot;
import com.RGR.Auction.models.Role;
import com.RGR.Auction.repositories.LotRepositories;
import com.RGR.Auction.repositories.Repositories;
import com.RGR.Auction.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;


@Service
public class DataService implements UserDetailsService {
   @Autowired
   Repositories Repository;
   @Autowired
   MailSender mailSender;
   @Autowired
   RoleRepo roleRepo;
   @Autowired
   LotRepositories lotRepositories;

   @Override
   public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

      Data user = Repository.findByMail(mail);

      if (user == null) {
         throw new UsernameNotFoundException("User not found");
      }

      return user;
   }

   public Data findUserById(Long userId) {
      Optional<Data> userFromDb = Repository.findById(userId);
      return userFromDb.orElse(new Data());
   }

   public boolean saveUser(String name,String surname,String mail,String password) {
      Data userFromDB = Repository.findByMail(mail);
      if (userFromDB != null) {
         return false;
      }
      else
      {
         Data newUser = new Data(name,surname,mail,password);
         newUser.setRoles(Collections.singleton(new Role(1L, "ROLE_ADMIN")));
         newUser.setPassword( new BCryptPasswordEncoder().encode(password));
         newUser.setActivation("false");
         newUser.setActivationCode(UUID.randomUUID().toString());
         Repository.save(newUser);
         if(!StringUtils.isEmpty(newUser.getMail()))
         {
            newUser.getUsername();
            newUser.getActivationCode();
            String message=String.format(newUser.getActivationCode());
            mailSender.send(newUser.getMail(),"Activation code", message);
         }
         return true;
      }
   }

   public void addFav(Data user,Lot lot)
   {
      user.addLot(lot);
      Repository.save(user);
   }


}
