package com.RGR.Auction.controllers;

import com.RGR.Auction.Service.DataService;
import com.RGR.Auction.models.Data;
import com.RGR.Auction.repositories.Repositories;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class RegistrationController {
    @Autowired
    private DataService dataService;
    @Autowired
    private Repositories Repository;

    @GetMapping("/registr") public String showRegistration(Model model)
    {

        return "registr";
    }


    //test registrathion
    @GetMapping("/reg") public String showTest(Model model)
    {

        return "reg";
    }
    /*
    (@RequestParam("name") String name, @RequestParam("surname") String surname,
    @RequestParam("fathername") String fathername, @RequestParam("birthdate") Date birthdate,
    @RequestParam("mail") String mail, @RequestParam("phone") String phone,
    @RequestParam("password") String password, @RequestParam("address") String address,
    @RequestParam("cardInfo") String cardInfo,
    @RequestParam("gender") int gender, Model model)
     */

    @PostMapping("/registr")
    public String add(@RequestParam String name, @RequestParam String surname,
                      @RequestParam String fathername, @RequestParam Date birthdate,
                      @RequestParam String mail, @RequestParam String phone,
                      @RequestParam String password, @RequestParam String address,
                      @RequestParam String cardInfo,
                      @RequestParam int gender, Model model)
    {

       if(dataService.saveUser(name,surname,fathername,birthdate,gender,phone,
               mail,address,cardInfo,password))
       {
           return "redirect:registr/verification";
       }

         else {
           model.addAttribute("message", "user with this email already exists");

           return "registr";
       }

    }
    @GetMapping("/registr/verification")
    public String verification()
    {
        return  "verification";
    }
    @PostMapping("/registr/verification")
    public String verificationCode(@RequestParam String code)
    {
        Data user = Repository.findByActivationCode(code);
        if(user != null)
        {
            System.out.println(code+user.getActivation());
            user.setActivation("true");
            dataService.saveActivation(user);
            System.out.println(user.getActivation());
        }
        return  "redirect:/login";
    }
}
