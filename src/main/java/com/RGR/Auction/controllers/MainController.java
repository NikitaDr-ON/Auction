package com.RGR.Auction.controllers;

import com.RGR.Auction.Service.Favourites.FavouritesService;
import com.RGR.Auction.Service.Product.ProductService;
import com.RGR.Auction.Service.PurchaseSale.PurchaseSaleService;
import com.RGR.Auction.Service.UserServices.UserService;
import com.RGR.Auction.models.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	@Autowired
	ProductService prod;
	@Autowired
	FavouritesService favoriteService;
	@Autowired
	PurchaseSaleService ps;
	@Autowired
	UserService userService;

    
    @SuppressWarnings("null")
	@GetMapping(value={"/index","/"," "})
    public String showIndex( Model model) {

		List<Product> products =prod.getAllProducts();
		model.addAttribute("products",products);

		return "index";
    }
    
    @GetMapping("/index/id={id}")
    public String addFavorite(@PathVariable("id") int id, Model model) {

		User user=userService.getCurrentUser();
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

	@PostMapping ("/search")
	public String searchProducts(@RequestParam String strSearch, Model model) {
		User user=userService.getCurrentUser();

		model.addAttribute("str",strSearch);
		List<Product> products=prod.searchProduct(user, strSearch);

		if(!products.isEmpty()) {
			String result="По вашему запросу \""+strSearch+"\" были найдены товары...";
			model.addAttribute("result",result);
			model.addAttribute("products",products);
		}
		else {
			String result="К сожалению, по вашему запросу товаров не найдено :( ";
			model.addAttribute("result",result);
		}

		return "search";
	}

}