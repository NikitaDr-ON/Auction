package com.RGR.Auction.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.RGR.Auction.Service.Delivery.DeliveryService;
import com.RGR.Auction.Service.DataService;
import com.RGR.Auction.Service.Favourites.FavouritesService;
import com.RGR.Auction.Service.Product.ProductService;
import com.RGR.Auction.Service.PurchaseSale.PurchaseSaleService;
import com.RGR.Auction.Service.TypeDelivery.TypeDeliveryService;
import com.RGR.Auction.models.*;
import com.RGR.Auction.repositories.Repositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/ajax")
public class AjaxController {

    @Autowired
    FavouritesService favService;
    @Autowired
    DataService userService;
    @Autowired
    DeliveryService del;
    @Autowired
    ProductService prod;
    @Autowired
    PurchaseSaleService purchaseService;
    @Autowired
    TypeDeliveryService services;
    @Autowired
    Repositories dataRepo;


    @GetMapping("/get_all_products")
    public List<Product> getAllProducts() {
        return prod.getAllProducts();
    }
    @GetMapping("/get_fav_products")
    public List<Product> getFavProducts(@AuthenticationPrincipal Data user) {
        return favService.getFavProductsByIdUser(user);
    }
    @GetMapping("/get_goods_purchase_sale")
    public List<Product> getGoodsPurchaseSale() {
        return prod.getProductsFromPurchaseSale();
    }
    @GetMapping("/get_purchase")
    public List<PurchaseSale> getPurchaseSale() {
        return purchaseService.getAllPurchaseSaleByBuyer();
    }
    @GetMapping("/get_services")
    public List<ServiceModel> getServices() {
        return services.getAllServices();
    }

/*
    @GetMapping("/show_message")
    public int showMessageSum() {
        List<Delivery> activDeliveries=del.getActiveDeliveriesByUser();
        List<Integer> salesDeliveries = new ArrayList<>();
        List<PurchaseSale> listSale=new ArrayList<>();

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

        return sum;
    }

 */


    @PostMapping("/add_fav_product")
    public void addFavProduct(@AuthenticationPrincipal Data user, @RequestBody Request request) {
       int stat=request.getStat();
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
    public void addPurchaseSale(@AuthenticationPrincipal Data user, @RequestParam("product_id") int product_id, @RequestParam("purchase_quant") int purchase_quant) throws IOException {

        System.out.println("product_id= "+product_id);
        System.out.println("purchase_quant= "+purchase_quant);

        purchaseService.addPurchaseSale(user,prod.getProductById(product_id),purchase_quant);

    }
    @ResponseBody
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public void cancel(@AuthenticationPrincipal Data user,@RequestParam("cancel") int canc) throws IOException {
        del.deleteActiveDeliveriesByUser(user);
    }
    @ResponseBody
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public void pay(@AuthenticationPrincipal Data user, @RequestParam("pay") int pay) throws IOException {
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



    @GetMapping("/get_vintage")
    public List<Product> getVintage() {
        List<Product> products = prod.getAllProducts();
        List<Product> vintage = new ArrayList<>();
        for(int i=0;i< products.size();i++)
        {
            if(products.get(i).getCategory()==2)
                vintage.add(products.get(i));
        }
        return vintage;
    }
    @GetMapping("/get_antic")
    public List<Product> getAntic() {
        List<Product> products = prod.getAllProducts();
        List<Product> antic = new ArrayList<>();
        for(int i=0;i< products.size();i++)
        {
            if(products.get(i).getCategory()==1)
                antic.add(products.get(i));
        }
        return antic;
    }
    @GetMapping("/get_handmade")
    public List<Product> getHandmade(){
        List<Product> products = prod.getAllProducts();
        List<Product> handmade = new ArrayList<>();
        for(int i=0;i< products.size();i++)
        {
            if(products.get(i).getCategory()==4)
                handmade.add(products.get(i));
        }
        return handmade;
    }

    @GetMapping("/get_collectable")
    public List<Product> getCollectable() {
        List<Product> products = prod.getAllProducts();
        List<Product> collectable = new ArrayList<>();
        for(int i=0;i< products.size();i++)
        {
            if(products.get(i).getCategory()==3)
                collectable.add(products.get(i));
        }
        return collectable;
    }

    @GetMapping("/get_drag")
    public List<Product> getDrag() {
        List<Product> products = prod.getAllProducts();
        List<Product> jewelry = new ArrayList<>();
        for(int i=0;i< products.size();i++)
        {
            if(products.get(i).getCategory()==5)
                jewelry.add(products.get(i));
        }
        return jewelry;
    }

    @GetMapping("/get_user")
    public Optional<Data> getUser(@AuthenticationPrincipal Data usersup) {
        Optional<Data> user = Optional.ofNullable(dataRepo.findById(usersup.getId()));
        user.ifPresent(data -> System.out.println(data.getId()));
        return user;
    }

    public static class Request {
        public int favoriteProd;
        public int stat;

        public int getFavorite() {
            return favoriteProd;
        }

        public void setFavorite(int favoriteProd) {
            this.favoriteProd = favoriteProd;
        }

        public int getStat() {
            return stat;
        }

        public void setStat(int stat) {
            this.stat = stat;
        }
    }
}

