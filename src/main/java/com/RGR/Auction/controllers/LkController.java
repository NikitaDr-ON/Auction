package com.RGR.Auction.controllers;

import com.RGR.Auction.Service.Delivery.DeliveryService;
import com.RGR.Auction.Service.Favourites.FavouritesService;
import com.RGR.Auction.Service.Product.ProductService;
import com.RGR.Auction.Service.PurchaseSale.PurchaseSaleService;
import com.RGR.Auction.Service.TypeDelivery.TypeDeliveryService;
import com.RGR.Auction.Service.UserServices.UserService;
import com.RGR.Auction.models.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class LkController {

	@Autowired
	DeliveryService deliveryService;
	@Autowired
	FavouritesService favService;
	@Autowired
	ProductService prod;
	@Autowired
	PurchaseSaleService purchaseService;
	@Autowired
	TypeDeliveryService servicesService;
	@Autowired
	UserService userService;

/*Покупка товаров(кнопка *купить*)*/
	@GetMapping("/lk/buy")
	public String buy( Model model) {
		User user=userService.getCurrentUser();
		List<Delivery> activDeliveries=deliveryService.getActiveDeliveriesByUser(user);
		List<Integer> salesDeliveries = new ArrayList<>();
		List<PurchaseSale> listSale=new ArrayList<>();
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
	public String getFavouritesProducts( Model model) {

		User user=userService.getCurrentUser();
		//для значка админки
		if(user.getRole().name().equals("ADMIN")){
			int roleStatus=1;
		model.addAttribute("role",roleStatus);
		}
		//корзина
		List<Product> productBasket=prod.getProductsFromPurchaseSale();
		List<PurchaseSale> ps=purchaseService.getAllPurchaseSaleByBuyer(user);
		Map<Product,PurchaseSale> map=new HashMap<Product,PurchaseSale>();
		if(ps.isEmpty()){
			String resultBasket="Пока здесь пусто :(    Добавьте товары в корзину, перейдя в карточку продукта ";
			model.addAttribute("resultBasket",resultBasket);
		} else {
			for (int i = 0; i < ps.size(); i++) {
				map.put(productBasket.get(i), ps.get(i));
			}
			model.addAttribute("mapProducts", map);
		}
		//сервисы доставки
		List<ServiceModel> services=servicesService.getAllServices();
		model.addAttribute("services",services);
		//избранное
		List<Product> products =favService.getFavProductsByIdUser(user);
		if(!products.isEmpty()) {
			model.addAttribute("favproducts",products);
		}
		else {
			String result="Пока здесь пусто :(    Добавьте товары, кликнув на ♡ в карточке продукта ";
			model.addAttribute("resultFav",result);
		}
		//заказы
		List <PurchaseSale> psDelivery=purchaseService.getAllPurchaseSaleWithOldByUser(user);
		List<Delivery>deliveries=deliveryService.getDeliveriesByUser(user);
		Map<PurchaseSale,Delivery> mapOrders=new HashMap<PurchaseSale,Delivery>();
		if(deliveries.isEmpty()){
			String resultOrder="Заказов нет";
			model.addAttribute("resultOrder",resultOrder);
		} else {
			for (int i = 0; i < deliveries.size(); i++) {
				mapOrders.put(psDelivery.get(i), deliveries.get(i));
			}
			model.addAttribute("mapOrders", mapOrders);
		}



		return "lk";
	}

	@GetMapping("/admin")
	public String adminka( Model model) {
		User user=userService.getCurrentUser();
		//пользователи
		List<User> users= userService.findAll();
		model.addAttribute("users",users);
		//товары
		List<Product> products =prod.getAllProducts();
		model.addAttribute("products",products);
		model.addAttribute("currentUser",user.getId());
		//заказы
		List <PurchaseSale> psDelivery=purchaseService.getAllPurchaseSaleWithOld();
		List<Delivery>deliveries=deliveryService.getAllDeliveries();
		model.addAttribute("userDelivery", users);
		Map<PurchaseSale,Delivery> mapOrders=new HashMap<PurchaseSale,Delivery>();
		if(deliveries.isEmpty()){
			String resultOrder="Заказов нет";
			model.addAttribute("resultOrder",resultOrder);
		} else {
			for (int i = 0; i < deliveries.size(); i++) {
				mapOrders.put(psDelivery.get(i), deliveries.get(i));
			}
			model.addAttribute("mapOrders", mapOrders);
		}


		return "admin";
	}

	@GetMapping("/admin/deleteUserId={id}")
	public String deleteUsers(@PathVariable("id") int id, Model model) {

		userService.deleteUser(id);
		return "redirect:/admin";
	}
	@GetMapping("/admin/deleteProductId={id}")
	public String deleteProducts(@PathVariable("id") int id, Model model) {

		prod.deleteProduct(id);
		return "redirect:/admin";
	}
	@GetMapping("/admin/userId={id}")
	public String showProduct(@PathVariable("id") int idUser, Model model) {

		User users= userService.findUserById(idUser);
		model.addAttribute("userDelivery",users);

		return "users";
	}



}
