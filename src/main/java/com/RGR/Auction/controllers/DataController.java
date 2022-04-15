package com.RGR.Auction.controllers;

import com.RGR.Auction.models.Data;
import com.RGR.Auction.repositories.Repositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DataController {
    private final Repositories Repositories;
    @Autowired
    public DataController(Repositories Repositories) {
        this.Repositories = Repositories;
    }
    @GetMapping("/authorization") public String authorization(Model model)
        {
            Iterable<Data> data =  Repositories.findAll();
            model.addAttribute("data",data);
            return "authorization";
        }
    }
