package com.RGR.Auction.controllers;

import com.RGR.Auction.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.RGR.Auction.Service.Category.CategoryService;

@Controller
public class CategoryController {
 @Autowired
    CategoryService categoryService;

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
    @GetMapping("/sdelsruk")
    public String sdelsruk(Model model) {

        return "sdelsruk";
    }
    @GetMapping("/vintag")
    public String vintag(Model model) {
       /* Iterable<Lot> lots = lotRepository.findAll();
        List<Lot> vintage = new ArrayList<>();
        lots.forEach(vintage::add);
        for(int i=0;i< vintage.size();i++)
        {
            if(vintage.get(i).getCategory()!=1)//у меня vintage под id=1
                vintage.remove(i);
        }
        model.addAttribute("vintage",vintage);
        System.out.println(vintage);*/
        return "vintag";
    }

}
