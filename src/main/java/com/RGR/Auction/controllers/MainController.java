package com.RGR.Auction.controllers;

import com.RGR.Auction.Service.Favourites.FavouritesService;
import com.RGR.Auction.Service.Product.ProductService;
import com.RGR.Auction.Service.PurchaseSale.PurchaseSaleService;
import com.RGR.Auction.models.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {
	@Autowired
	ProductService prod;
	@Autowired
	FavouritesService favoriteService;
	@Autowired
	PurchaseSaleService ps;

    
    @SuppressWarnings("null")
	@GetMapping("/index")
    public String showIndex( Model model) {

        return "index";
    }
    
    @GetMapping("/index/id={id}")
    public String addFavorite(@PathVariable("id") int id, @AuthenticationPrincipal Data user, Model model) {

		model.addAttribute("id_product",id);
		List<Product> products=favoriteService.getFavProductsByIdUser(user);
		int status=0;
		if(!products.isEmpty()) {
			for (Product favProd : products) {
				if (favProd.getId() == id) {
					status = 1;
				}
			}
		}
		model.addAttribute("stat_fav_product",status);
		model.addAttribute("products",prod.getProductById(id));

		return "product";
    }
}