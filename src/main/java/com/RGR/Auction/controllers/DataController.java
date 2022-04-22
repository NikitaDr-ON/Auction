package com.RGR.Auction.controllers;

import com.RGR.Auction.models.Data;
import com.RGR.Auction.repositories.Repositories;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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


    @PostMapping("/authorization")
    public String add(@RequestParam String name,@RequestParam String surname,@RequestParam String mail,@RequestParam String password,Model model)
    {
        Data user = new Data(name,surname,mail,password);
        Repositories.save(user);
        Iterable<Data> data =  Repositories.findAll();
        model.addAttribute("data",data);
        return "authorization";
    }
    }
