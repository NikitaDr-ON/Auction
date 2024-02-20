package com.RGR.Auction.controllers;

import com.RGR.Auction.Service.UserServices.UserService;
import com.RGR.Auction.models.User;
import com.RGR.Auction.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class RegistrationController {
    @Autowired
    private UserService dataService;
    @Autowired
    private UserRepository Repository;

    @GetMapping("/registr") public String showRegistration(Model model)
    {

        return "registr";
    }


    //test registrathion
    @GetMapping("/reg") public String showTest(Model model)
    {

        return "reg";
    }

    @PostMapping("/registr")
    public String add(@RequestParam String name, @RequestParam String surname,
                      @RequestParam String fathername, @RequestParam String birthdate,
                      @RequestParam String mail, @RequestParam String phone,
                      @RequestParam String password, @RequestParam String city,
                      @RequestParam String street,@RequestParam String house,
                      @RequestParam String apartment,@RequestParam String cardInfo,
                      @RequestParam int gender, Model model)
    {

        String address=city+","+street+","+house+","+apartment;

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
        User user = Repository.findByActivationCode(code);
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
