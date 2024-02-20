package com.RGR.Auction.controllers;

import java.io.IOException;
import java.util.List;

import com.RGR.Auction.Service.Delivery.DeliveryService;
import com.RGR.Auction.Service.Favourites.FavouritesService;
import com.RGR.Auction.Service.Product.ProductService;
import com.RGR.Auction.Service.PurchaseSale.PurchaseSaleService;
import com.RGR.Auction.Service.TypeDelivery.TypeDeliveryService;
import com.RGR.Auction.Service.UserServices.UserService;
import com.RGR.Auction.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/ajax")
public class AjaxController {

    @Autowired
    FavouritesService favService;
    @Autowired
    UserService userService;
    @Autowired
    DeliveryService del;
    @Autowired
    ProductService prod;
    @Autowired
    PurchaseSaleService purchaseService;
    @Autowired
    TypeDeliveryService services;


    @GetMapping("/get_goods_purchase_sale")
    public List<Product> getGoodsPurchaseSale() {
        return prod.getProductsFromPurchaseSale();
    }
    @GetMapping("/get_purchase")
    public List<PurchaseSale> getPurchaseSale() {
        User user=userService.getCurrentUser();
        return purchaseService.getAllPurchaseSaleByBuyer(user);
    }

    @PostMapping("/add_fav_product")
    public void addFavProduct( @RequestBody Request request) {
       int stat=request.getStat();
        User user=userService.getCurrentUser();
       int idFav=request.getFavorite();
       Favourites fav=new Favourites();
       fav.setProduct_id(idFav);
       //fav.setUser_id((int)userService.getCurrentUser().getId());
        fav.setUser_id((int)user.getId());
        if(stat==1) {
           favService.addFavProductByObj(fav);
       }
       if(stat==0){
           favService.deleteFavProductByObj(fav);
       }

    }
    @ResponseBody
    @RequestMapping(value = "/add_purchase_sale", method = RequestMethod.POST)
    public void addPurchaseSale( @RequestParam("product_id") int product_id, @RequestParam("purchase_quant") int purchase_quant) throws IOException {

        User user=userService.getCurrentUser();
        System.out.println("product_id= "+product_id);
        System.out.println("purchase_quant= "+purchase_quant);

        purchaseService.addPurchaseSale(user,prod.getProductById(product_id),purchase_quant);

    }
    @ResponseBody
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public void cancel(@RequestParam("cancel") int canc) throws IOException {
        User user=userService.getCurrentUser();
        del.deleteActiveDeliveriesByUser(user);
    }
    @ResponseBody
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public void pay( @RequestParam("pay") int pay) throws IOException {
        User user=userService.getCurrentUser();
        del.payDelivery(user);
    }
    @ResponseBody
    @RequestMapping(value = "/delete_purchase_sale", method = RequestMethod.POST)
    public void deletePS(@RequestParam("idSale") int idSale) throws IOException {
        purchaseService.deletePurchaseSale(idSale);
    }

    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public void buy(@RequestParam("arraySale[]") List<Integer> myArray, @RequestParam("service") int serviceId) throws IOException {

        System.out.println("myArray[]= "+myArray.toString());
        System.out.println("serviceid= "+serviceId);
        for(int el: myArray){
        del.addDelivery(el,serviceId);

        }
    }


    public static class Request {
        public int favoriteProd;
        public int stat;

        public int getFavorite() {
            return favoriteProd;
        }

        public int getStat() {
            return stat;
        }
    }
}

