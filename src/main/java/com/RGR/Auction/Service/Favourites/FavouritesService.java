package com.RGR.Auction.Service.Favourites;

import com.RGR.Auction.models.Favourites;
import com.RGR.Auction.models.Product;
import com.RGR.Auction.models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public interface FavouritesService {
   List<Product> getFavProductsByIdUser(User user);

    void addFavProduct(User user, int idProduct);

    void addFavProductByObj(Favourites fav);

    @Transactional
    void deleteFavProduct(User user, int idProduct);

    @Transactional
    void deleteFavProductByObj(Favourites fav);
}
