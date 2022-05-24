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

@Controller
public class RegistrationController {
    @Autowired
    private DataService dataService;
    @Autowired
    Repositories Repository;

    @GetMapping("/registration") public String showRegistration(Model model)
    {

        return "registration";
    }


    @PostMapping("/registration")
    public String add(@RequestParam String name,@RequestParam String surname,@RequestParam String mail,@RequestParam String password)
    {
       dataService.saveUser(name,surname,mail,password);
        return "redirect:registration/verification";
    }
    @GetMapping("/registration/verification")
    public String verification()
    {
        return  "verification";
    }
    @PostMapping("/registration/verification")
    public String verificationCode(@RequestParam String code)
    {
        Data user = Repository.findByActivationCode(code);
        if(user != null)
        {
            System.out.println(code+user.getActivation());
            user.setActivation("true");
            Repository.save(user);
            System.out.println(user.getActivation());
        }
        return  "verification";
    }
}
