package com.RGR.Auction.Service.Favourites;

import com.RGR.Auction.models.Data;
import com.RGR.Auction.models.Favourites;
import com.RGR.Auction.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public interface FavouritesService {
    List<Product> getFavProductsByIdUser(Data user);

    void addFavProduct(Data user, int idProduct);

    void addFavProductByObj(Favourites fav);

    @Transactional
    void deleteFavProduct(Data user, int idProduct);

    @Transactional
    void deleteFavProductByObj(Favourites fav);
}
