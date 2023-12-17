package com.RGR.Auction.controllers;

import com.RGR.Auction.Service.DataService;
import com.RGR.Auction.models.Data;
import com.RGR.Auction.repositories.Repositories;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

/*
    @PostMapping("/authorization")
    public String add(@RequestParam String name, @RequestParam String surname, @RequestParam String mail, @RequestParam String password, Model model) {
        Data user = new Data(name, surname, mail, password);
        Repositories.save(user);
        Iterable<Data> data = Repositories.findAll();
        model.addAttribute("data", data);
        return "authorization";
    }
*/

    @GetMapping("/hello")
    public String activate(Model model) {
       /* boolean isActivated = dataService.activateUser(code);
        if(isActivated)
        {
            model.addAttribute("message","Регистрация завершена");
        }*/
        return "hello";
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
