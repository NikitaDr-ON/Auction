package com.RGR.Auction.Service.Product;

import com.RGR.Auction.models.Product;
import com.RGR.Auction.models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public interface ProductService {

    List<Product> getAllProducts();

    void addProduct(String name, String description, String photo, int category, int price, int quant, int seller);

    Product getProductById(int id);

    void deleteProduct(int id);

    void changeProductQuant(int idProduct, int changeQuantTo);

    List <Product> searchProduct(User user, String str);

    List<Product> getProductsFromPurchaseSale();
}
