package com.RGR.Auction.controllers;

import com.RGR.Auction.Service.UserServices.UserService;
import com.RGR.Auction.models.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@NoArgsConstructor
public class DataController {
    @Autowired
    UserService userService;



    @RequestMapping("/login")
    public String login(Model model) {

        return "login";
    }
      @GetMapping("/zabpar")
      public String zabpar(Model model) {

          return "zabpar";
      }



}
