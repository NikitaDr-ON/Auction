package com.RGR.Auction.controllers;

import com.RGR.Auction.models.Data;
import com.RGR.Auction.models.Favourites;

import java.util.Collection;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	
	 public Collection <Favourites> fav=null;

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
    public String showIndex( Model model) {
        return "index";
    }
    
    @GetMapping("index/id={id}")
    public String addFavourites(@PathVariable("id") int id, @AuthenticationPrincipal Data user, Model model) {
        
    	fav.add(new Favourites(id,user.getId()));
    	System.out.println(fav.toString());
        return "index";
    }


}