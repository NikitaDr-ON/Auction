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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/registr")
    public String add(@RequestParam String name, @RequestParam String surname, @RequestParam String mail, @RequestParam String password,
                      @RequestParam String fathername, @RequestParam Date birthdate, @RequestParam int gender, @RequestParam String phone,
                      @RequestParam String address, @RequestParam String cardInfo, Model model)
    {
       if(dataService.saveUser(name,surname,fathername,birthdate,gender,phone,
               mail,address,cardInfo,password))
       {
           return "redirect:registr/verification";
       }
       else {
           model.addAttribute("message","user with this email already exists");
           return  "registr";
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
