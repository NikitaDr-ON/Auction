package com.RGR.Auction.controllers;

import com.RGR.Auction.Service.DataService;
import com.RGR.Auction.models.Data;
import com.RGR.Auction.repositories.Repositories;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Controller
public class DataController {

    @Autowired
    private Repositories Repositories;
    @Autowired
    DataService dataService;

    @GetMapping("/authorization")
    public String authorization(Model model) {
        Iterable<Data> data = Repositories.findAll();
        model.addAttribute("data", data);
        return "authorization";
    }

    @GetMapping("/privateOffice/{id}")
    public String privateOffice(@PathVariable(value = "id") long userId,Model model) {
        Optional<Data> user = Repositories.findById(userId);
        ArrayList<Data> resultat = new ArrayList<>();
        user.ifPresent(resultat::add);
        model.addAttribute("data",resultat);
        return "privateOffice/{id}";
    }


    @PostMapping("/authorization")
    public String add(@RequestParam String name, @RequestParam String surname, @RequestParam String mail, @RequestParam String password, Model model) {
        Data user = new Data(name, surname, mail, password);
        Repositories.save(user);
        Iterable<Data> data = Repositories.findAll();
        model.addAttribute("data", data);
        return "authorization";
    }

    @GetMapping("/hello")
    public String activate(Model model) {
       /* boolean isActivated = dataService.activateUser(code);
        if(isActivated)
        {
            model.addAttribute("message","Регистрация завершена");
        }*/
        return "hello";
    }

    @GetMapping("/antikiick")
    public String antikiick(Model model) {

        return "antikiick";
    }

    @GetMapping("/collec")
    public String collec(Model model) {

        return "collec";
    }

    @GetMapping("/dragiukr")
    public String dragiukr(Model model) {

        return "dragiukr";
    }
    @GetMapping("/lc")
    public String lc(Model model) {

        return "lc";
    }
    @GetMapping("/sdelsruk")
    public String sdelsruk(Model model) {

        return "sdelsruk";
    }
    @GetMapping("/vintag")
    public String vintag(Model model) {

        return "vintag";
    }
}
