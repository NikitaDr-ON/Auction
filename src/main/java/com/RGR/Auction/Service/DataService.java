package com.RGR.Auction.Service;

import com.RGR.Auction.models.Data;
import com.RGR.Auction.models.Role;
import com.RGR.Auction.models.User;
import com.RGR.Auction.repositories.Repositories;
import com.RGR.Auction.repositories.RoleRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class DataService implements UserDetailsService {
   @Autowired
   private Repositories dataRepo;
   @Autowired
   MailSender mailSender;
   @Autowired
   RoleRepo roleRepo;

   SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

   @Override
   public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

      Data user = dataRepo.findByMail(mail);


      if (user == null) {
         throw new UsernameNotFoundException("User not found");
      }

      return user;
   }

   public Data findUserById(Long userId) {
      Optional<Data> userFromDb = dataRepo.findById(userId);
      return userFromDb.orElse(new Data());
   }

   public boolean saveUser(String name,String surname,String fathername, Date birthdate,int gender,String phone,
           String mail,String address,String cardInfo,String password) {

      Data userFromDB = dataRepo.findByMail(mail);
      if (userFromDB != null) {
         return false;
      }
      else
      {
         Data newUser = new Data(name,surname,fathername,birthdate,gender,phone,
                 mail,address,cardInfo,password);
         newUser.setRoles(Collections.singleton(new Role(1L, "ROLE_ADMIN")));
         newUser.setPassword( new BCryptPasswordEncoder().encode(password));
         newUser.setActivation("false");
         newUser.setActivationCode(UUID.randomUUID().toString());
         dataRepo.save(newUser);

/*
         String pass = new BCryptPasswordEncoder().encode(password);
         String activation="false";
         String activationCode=UUID.randomUUID().toString();

         Session session = sessionFactory.openSession();
         org.hibernate.Transaction tr = session.beginTransaction();

         String sqlQuery =  "INSERT INTO users (name,surname,fathername,birthdate,gender,phone," +
                 "mail,address,cardInfo,password,Activation_code,Activation)"+
                 "VALUES (name=:name,surname=:surname,fathername=:fathername," +
                 "birthdate=:birthdate,gender=:gender,phone=:phone," +
                 "mail=:mail,address=:address,cardInfo=:cardInfo," +
                 "password=:password,Activation_code=:Activation_code,Activation=:Activation)";

         Query query = session.createNativeQuery(sqlQuery);
         query.setParameter("name", name);
         query.setParameter("surname", surname);
         query.setParameter("fathername", fathername);
         query.setParameter("birthdate", birthdate);
         query.setParameter("gender", gender);
         query.setParameter("phone", phone);
         query.setParameter("mail", mail);
         query.setParameter("address", address);
         query.setParameter("cardInfo", cardInfo);
         query.setParameter("password", pass);
         query.setParameter("Activation_code", activationCode);
         query.setParameter("Activation", activation);

         int result = query.executeUpdate();
         System.out.println("Rows affected: " + result);
         tr.commit();
*/

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
@Transactional
   public User getCurrentUser(){
      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      String username="Unnamed";
      if (principal instanceof UserDetails) {
         username = ((UserDetails)principal).getUsername();
      } else {
         username = principal.toString();
      }
      System.out.println("userActiv= "+username);

      String sqlQuery = "SELECT ID,name,surname,fathername,birthdate,gender,phone,mail,address,cardInfo FROM users " +
              "WHERE name=:username";

   Session session = sessionFactory.openSession();
   org.hibernate.Transaction tr = session.beginTransaction();
      List<User> users= session.createNativeQuery(sqlQuery).setParameter("username", username)
              .setResultTransformer(Transformers.aliasToBean(User.class)).list();
     tr.commit();

     return users.get(0);
   }
/*
   @Transactional
   public Data findByMail(String mail){

      String sqlQuery = "SELECT ID,name,surname,fathername,birthdate,gender,phone,mail,address,cardinfo,Password FROM users " +
              "WHERE mail=\""+mail+"\" ";

      Session session = sessionFactory.openSession();
      org.hibernate.Transaction tr = session.beginTransaction();
      List<Data> users=session.createNativeQuery(sqlQuery)
              .setResultTransformer(Transformers.aliasToBean(Data.class)).list();
      tr.commit();

      return users.get(0);
   }
   @Transactional
   public Optional<Data> findById(long id){

      String sqlQuery = "SELECT ID,name,surname,fathername,birthdate,gender,phone,mail,address,cardinfo,Password FROM users " +
              "WHERE ID=:id";

      Session session = sessionFactory.openSession();
      org.hibernate.Transaction tr = session.beginTransaction();
      List<Data> users=session.createNativeQuery(sqlQuery).setParameter("id", id)
              .setResultTransformer(Transformers.aliasToBean(Data.class)).list();
      tr.commit();

      return Optional.ofNullable(users.get(0));
   }

   @Transactional
   public Data findByActivationCode(String code) {
      String sqlQuery = "SELECT ID,name,surname,fathername,birthdate,gender,phone,mail,address,cardInfo,Password FROM users " +
              "WHERE Activation_code=:code";

      Session session = sessionFactory.openSession();
      org.hibernate.Transaction tr = session.beginTransaction();
      List<Data> users=session.createNativeQuery(sqlQuery).setParameter("code", code)
              .setResultTransformer(Transformers.aliasToBean(Data.class)).list();
      tr.commit();

      return users.get(0);
   }

 */
   @Transactional
   public void saveActivation(Data user) {
      Session session = sessionFactory.openSession();
      org.hibernate.Transaction tr = session.beginTransaction();

      String sqlQuery =  "UPDATE users SET Activation=\"true\", " +
              "WHERE id=:id";
      Query query = session.createNativeQuery(sqlQuery);
      query.setParameter("id", user.getId());

      int result = query.executeUpdate();
      System.out.println("Rows update: " + result);
      tr.commit();

   }
}
