package com.RGR.Auction.controllers;

import com.RGR.Auction.models.Data;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

   /* @GetMapping("/hello")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }*/

    @PostMapping("/antikiick")
    public String show(@RequestParam String name,@RequestParam String surname,@RequestParam String mail,@RequestParam String password,Model model)
    {
        return "antikiick";
    }
    @GetMapping("/index")
    public String showIndex(@AuthenticationPrincipal Data user, Model model) {
        model.addAttribute("userID",user.getId());
        return "index";
    }


}