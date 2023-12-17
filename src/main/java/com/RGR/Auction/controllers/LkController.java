package com.RGR.Auction.controllers;

import com.RGR.Auction.Service.DataService;
import com.RGR.Auction.Service.Delivery.DeliveryService;
import com.RGR.Auction.Service.Product.ProductService;
import com.RGR.Auction.Service.PurchaseSale.PurchaseSaleService;
import com.RGR.Auction.models.Data;
import com.RGR.Auction.models.Delivery;
import com.RGR.Auction.models.PurchaseSale;
import com.RGR.Auction.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.RGR.Auction.repositories.Repositories;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LkController {
	@Autowired
 	private Repositories userRepository;

	@Autowired
	DeliveryService deliveryService;

	@Autowired
	ProductService prod;
	@Autowired
	PurchaseSaleService purchaseService;

/*Покупка товаров(кнопка *купить*)*/
	@GetMapping("/lk/buy")
	public String buy( @AuthenticationPrincipal Data user, Model model) {

		List<Delivery> activDeliveries=deliveryService.getActiveDeliveriesByUser(user);
		List<Integer> salesDeliveries = new ArrayList<>();
		List<PurchaseSale> listSale=new ArrayList<>();
		//String cardInfoUser= new DataService().getCurrentUser().getCardInfo();
		String cardInfoUser= user.getCardInfo();

		for(Delivery deliv: activDeliveries){
			salesDeliveries.add(deliv.getId_sale());
		}

		for (int nd : salesDeliveries) {
			listSale.add(purchaseService.getPurchaseSaleById(nd));
		}

		int sum=0;
		for(int i=0;i<listSale.size();i++) {
			sum =sum+ listSale.get(i).getPurchase_quant()*listSale.get(i).getPurchase_price();
		}

		if(sum==0){
			return "redirect:/lk";
		}
		else{
			model.addAttribute("message",sum);
			model.addAttribute("schet", cardInfoUser);
			return "buy";
		}
	}



	@RequestMapping(path = "/lk", method = RequestMethod.GET)
	public String getUsers(@AuthenticationPrincipal Data user, Model model) {
		//System.out.println("user= "+user.getMail()+","+user.getId());

		return "lk";
	}

}
