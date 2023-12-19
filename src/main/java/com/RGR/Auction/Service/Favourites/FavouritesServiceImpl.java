package com.RGR.Auction.Service.Favourites;

import com.RGR.Auction.Service.DataService;
import com.RGR.Auction.models.Data;
import com.RGR.Auction.models.Favourites;
import com.RGR.Auction.models.Product;
import com.RGR.Auction.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class FavouritesServiceImpl implements FavouritesService{
    private static final Logger logger = LogManager.getLogger(FavouritesServiceImpl.class);

    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    @Override
    @Transactional
    public List<Product> getFavProductsByIdUser(Data user) {
       //int userid=(int)new DataService().getCurrentUser().getId();
        int userid=(int)user.getId();
        String sqlQuery = "SELECT id,name,description,photo,category,price,quant,seller FROM favourites " +
                "JOIN product ON favourites.product_id=product.id " +
                "WHERE user_id = :userId";
        Session session = sessionFactory.openSession();
        org.hibernate.Transaction tr = session.beginTransaction();
        List<Product> products=session.createNativeQuery(sqlQuery).setParameter("userId", userid)
                .setResultTransformer(Transformers.aliasToBean(Product.class)).list();
        //if(!products.isEmpty()){}
        tr.commit();

        return products;
    }

    @Override
    @Transactional
    public void addFavProduct(Data user,int idProduct){
        //int userId=new DataService().getCurrentUser().getId();
        int userId=(int)user.getId();
        String sqlQuery =  "INSERT INTO favourites VALUE(" +
                ":userId," +
                ":idProduct)";
        Session session = sessionFactory.openSession();
        org.hibernate.Transaction tr = session.beginTransaction();

        Query query = session.createNativeQuery(sqlQuery);
        query.setParameter("userId", userId);
        query.setParameter("idProduct", idProduct);
        int result = query.executeUpdate();
        System.out.println("Rows affected: " + result);
        tr.commit();

    }
    @Override
    @Transactional
    public void addFavProductByObj(Favourites fav){

        String sqlQuery =  "INSERT INTO favourites VALUE(" +
                ":userId," +
                ":idProduct)";
        Session session = sessionFactory.openSession();
        org.hibernate.Transaction tr = session.beginTransaction();

        Query query = session.createNativeQuery(sqlQuery);
        query.setParameter("userId", fav.getUser_id());
        query.setParameter("idProduct", fav.getProduct_id());
        int result = query.executeUpdate();
        System.out.println("Rows affected: " + result);
        tr.commit();
        logger.info("User with id("+fav.getUser_id()+") add favorite product with id("+fav.getProduct_id()+")");
    }

    @Override
    @Transactional
    public void deleteFavProduct( Data user, int idProduct) {
        //int userId=new DataService().getCurrentUser().getId();
        int userId=(int)user.getId();
        Session session = sessionFactory.openSession();
        org.hibernate.Transaction tr = session.beginTransaction();

        String sqlQuery =  "DELETE FROM favourites WHERE id_user=:idUser AND id_product=:idProduct";
        Query query = session.createNativeQuery(sqlQuery);
        query.setParameter("idUser",userId);
        query.setParameter("idProduct",idProduct);

        int result = query.executeUpdate();
        System.out.println("Rows delete: " + result);
       tr.commit();
        logger.info("User with id("+userId+") delete favorite product with id("+idProduct+")");
    }
    @Override
    @Transactional
    public void deleteFavProductByObj(Favourites fav) {
        Session session = sessionFactory.openSession();
        org.hibernate.Transaction tr = session.beginTransaction();

        String sqlQuery =  "DELETE FROM favourites WHERE user_id=:userId AND product_id=:idProduct";
        Query query = session.createNativeQuery(sqlQuery);
        query.setParameter("userId", fav.getUser_id());
        query.setParameter("idProduct", fav.getProduct_id());

        int result = query.executeUpdate();
        System.out.println("Rows delete: " + result);
        tr.commit();
        logger.info("User with id("+fav.getUser_id()+") delete favorite product with id("+fav.getProduct_id()+")");

    }

}
