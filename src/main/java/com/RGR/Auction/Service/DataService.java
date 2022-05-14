package com.RGR.Auction.Service;

import com.RGR.Auction.models.Data;
import com.RGR.Auction.repositories.Repositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class DataService implements UserDetailsService {
   @Autowired
   Repositories Repository;
   @Autowired
   MailSender mailSender;

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

   public void saveUser(String name,String surname,String mail,String password) {
      Data userFromDB = Repository.findByMail(mail);
      if (userFromDB != null) {
      }
      else
      {
         Data newUser = new Data(name,surname,mail,password);
         newUser.setPassword( new BCryptPasswordEncoder().encode(password));
         newUser.setActivation("true");
         newUser.setActivationCode(UUID.randomUUID().toString());
         Repository.save(newUser);
         if(!StringUtils.isEmpty(newUser.getMail()))
         {
            String message=String.format("Hello world");
            newUser.getUsername();
            newUser.getActivationCode();
            mailSender.send(newUser.getMail(),"Activation code", message);
         }
      }
   }

   public boolean activateUser(String code)
   {
      Data user = Repository.findByActivationCode(code);
      if(user==null) {
         return false;
      }
      else
      {
         user.setActivationCode(null);
         Repository.save(user);
         return  true;
      }
   }


}
