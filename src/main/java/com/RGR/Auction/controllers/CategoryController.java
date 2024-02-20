package com.RGR.Auction.controllers;

import com.RGR.Auction.Service.Product.ProductService;
import com.RGR.Auction.models.Category;
import com.RGR.Auction.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.RGR.Auction.Service.Category.CategoryService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService prod;


    @GetMapping("/antikiick")
    public String antikiick(Model model) {
        List<Product> products = prod.getAllProducts();
        List<Product> antic = new ArrayList<>();
        for(int i=0;i< products.size();i++)
        {
            if(products.get(i).getCategory()==1)
                antic.add(products.get(i));
        }
        model.addAttribute("products",antic);

        return "antikiick";
    }

    @GetMapping("/collec")
    public String collec(Model model) {
        List<Product> products = prod.getAllProducts();
        List<Product> collectable = new ArrayList<>();
        for(int i=0;i< products.size();i++)
        {
            if(products.get(i).getCategory()==3)
                collectable.add(products.get(i));
        }
        model.addAttribute("products",collectable);

        return "collec";
    }

    @GetMapping("/dragiukr")
    public String dragiukr(Model model) {

        List<Product> products = prod.getAllProducts();
        List<Product> jewelry = new ArrayList<>();
        for(int i=0;i< products.size();i++)
        {
            if(products.get(i).getCategory()==5)
                jewelry.add(products.get(i));
        }
        model.addAttribute("products",jewelry);


        return "dragiukr";
    }
    @GetMapping("/sdelsruk")
    public String sdelsruk(Model model) {

        List<Product> products = prod.getAllProducts();
        List<Product> handmade = new ArrayList<>();
        for(int i=0;i< products.size();i++)
        {
            if(products.get(i).getCategory()==4)
                handmade.add(products.get(i));
        }
        model.addAttribute("products",handmade);

        return "sdelsruk";
    }
    @GetMapping("/vintag")
    public String vintag(Model model) {
        List<Product> products = prod.getAllProducts();
        List<Product> vintage = new ArrayList<>();
        for(int i=0;i< products.size();i++)
        {
            if(products.get(i).getCategory()==2)
                vintage.add(products.get(i));
        }
        model.addAttribute("products",vintage);

        return "vintag";
    }

}
