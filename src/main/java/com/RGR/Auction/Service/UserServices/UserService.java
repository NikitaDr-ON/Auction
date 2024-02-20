package com.RGR.Auction.Service.UserServices;

import com.RGR.Auction.Service.MailSender;
import com.RGR.Auction.models.Role;
import com.RGR.Auction.models.User;
import com.RGR.Auction.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository usersRepository;
    @Autowired
    MailSender mailSender;

    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();


    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public void deleteUser(long userId){
        usersRepository.deleteById(userId);
    }

    public Optional<User> findUserByMail(String email) {
        return Optional.ofNullable(usersRepository.findByMail(email));
    }

    public List<User> findAll() {
        return usersRepository.findAll();
    }

    public boolean saveUser(String name, String surname, String fathername, String birthdate, int gender, String phone,
                            String mail, String address, String cardInfo, String password) {

        User userFromDB = usersRepository.findByMail(mail);
        if (userFromDB != null) {
            return false;
        }
        else
        {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = new Date();
            try {
                startDate = df.parse(birthdate);
                String newDateString = df.format(startDate);
                System.out.println(newDateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            User newUser = new User();
            newUser.setName(name);
            newUser.setSurname(surname);
            newUser.setFathername(fathername);
            newUser.setBirthdate(startDate);
            newUser.setGender(gender);
            newUser.setPhone(phone);
            newUser.setMail(mail);
            newUser.setAddress(address);
            newUser.setCardInfo(cardInfo);
            newUser.setRole(Role.USER);
            newUser.setPassword( new BCryptPasswordEncoder().encode(password));
            newUser.setActivation("false");
            newUser.setActivationCode(UUID.randomUUID().toString());
            usersRepository.save(newUser);


            if(!StringUtils.isEmpty(newUser.getMail()))
            {
                String message=String.format(newUser.getActivationCode());
                mailSender.send(newUser.getMail(),"Activation code", message);
            }
            return true;
        }
    }

    @Transactional
    public User getCurrentUser(){

        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        System.out.println("userActiv= "+username);
        User user=usersRepository.findByMail(username);

        return user;
    }

    @Transactional
    public void saveActivation(User user) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();

        String sqlQuery =  "UPDATE users SET Activation=\"true\" " +
                "WHERE ID=:id";
        Query query = session.createNativeQuery(sqlQuery);
        query.setParameter("id", user.getId());

        int result = query.executeUpdate();
        System.out.println("Rows update: " + result);
        tr.commit();

    }

    public User findUserById(int id) {
        return usersRepository.findById(id);
    }

}