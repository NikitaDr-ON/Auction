package com.RGR.Auction.controllers;

import com.RGR.Auction.Service.DataService;
import com.RGR.Auction.models.Data;
import com.RGR.Auction.models.Lot;
import com.RGR.Auction.repositories.LotRepositories;
import com.RGR.Auction.repositories.Repositories;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Controller
public class DataController {

    @Autowired
    private Repositories Repositories;
    @Autowired
    DataService dataService;
    @Autowired
    private LotRepositories lotRepository;

    @GetMapping("/authorization")
    public String authorization(Model model) {
        Iterable<Data> data = Repositories.findAll();
        model.addAttribute("data", data);
        System.out.println("id");
        return "authorization";
    }

    @GetMapping("/privateOffice")
    public String privateOffice(@AuthenticationPrincipal Data user, Model model) {

       System.out.println(user.getId());
       model.addAttribute("data",user);
        System.out.println("id");
        return "privateOffice";
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
        Iterable<Lot> lot = lotRepository.findAll();
        model.addAttribute("lot",lot);

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
    @GetMapping("/sdelsruk")
    public String sdelsruk(Model model) {

        return "sdelsruk";
    }
    @GetMapping("/vintag")
    public String vintag(Model model) {

        return "vintag";
    }
    @RequestMapping("/login")
    public String login(Model model) {

        return "login";
    }
    //@PostMapping("/login")
    //public String vhod(@RequestParam String mail,@RequestParam String password,Model model) {

      //  return "redirect:/lk";
   // }
    @GetMapping("/zabpar")
    public String zabpar(Model model) {

        return "zabpar";
    }

}
