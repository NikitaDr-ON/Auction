package com.RGR.Auction.controllers;

import com.RGR.Auction.Service.DataService;
import com.RGR.Auction.models.Data;
import com.RGR.Auction.repositories.Repositories;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class DataController {

    @Autowired
    private  Repositories Repositories;
    
    @GetMapping("/authorization") public String authorization(Model model)
        {
            Iterable<Data> data =  Repositories.findAll();
            model.addAttribute("data",data);
            return "authorization";
        }

    @GetMapping("/privateOffice") public String privateOffice(Model model)
    {

        return "privateOffice";
    }


    @PostMapping("/authorization")
    public String add(@RequestParam String name,@RequestParam String surname,@RequestParam String mail,@RequestParam String password,Model model)
    {
        Data user = new Data(name,surname,mail,password);
        Repositories.save(user);
        Iterable<Data> data =  Repositories.findAll();
        model.addAttribute("data",data);
        return "authorization";
    }
    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code)
    {
        boolean isActivated = DataService.activateUser(code);
        return "hello";
    }
    }
