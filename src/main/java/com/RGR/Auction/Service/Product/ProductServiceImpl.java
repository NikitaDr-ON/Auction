package com.RGR.Auction.Service.Product;

import com.RGR.Auction.Service.DataService;
import com.RGR.Auction.Service.Favourites.FavouritesServiceImpl;
import com.RGR.Auction.models.Product;
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
public class ProductServiceImpl implements ProductService{


    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);


    @Override
    @Transactional
    public List<Product> getAllProducts() {
        String sqlQuery = "SELECT * FROM product WHERE quant>0" ;

        Session session = sessionFactory.openSession();
        org.hibernate.Transaction tr = session.beginTransaction();
        List <Product> products=session.createNativeQuery(sqlQuery)
                .setResultTransformer(Transformers.aliasToBean(Product.class)).list();
        //if(!products.isEmpty()){}
        tr.commit();

        return products;
    }

    @Override
    @Transactional
    public void addProduct(String name, String description, String photo, int category, int price, int quant, int seller) {

            Session session = sessionFactory.openSession();
        org.hibernate.Transaction tr = session.beginTransaction();

        String sqlQuery =  "INSERT INTO product(name,description,photo,category,price,quant,seller) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        Query query = session.createNativeQuery(sqlQuery);
        query.setParameter(1, name);
        query.setParameter(2, description);
        query.setParameter(3, photo);
        query.setParameter(4, category);
        query.setParameter(5, price);
        query.setParameter(6, quant);
        query.setParameter(7, seller);

        int result = query.executeUpdate();
        System.out.println("Rows affected: " + result);
       tr.commit();
    }
    @Override
    @Transactional
    public Product getProductById(int id) {
        String sqlQuery = "SELECT * FROM product WHERE id=:idProduct" ;
        Session session = sessionFactory.openSession();
        org.hibernate.Transaction tr = session.beginTransaction();

        List<Product> products=session.createNativeQuery(sqlQuery).setParameter("idProduct", id)
                .setResultTransformer(Transformers.aliasToBean(Product.class)).list();
        tr.commit();
        return products.get(0);
    }
    @Override
    @Transactional
    public void deleteProduct(int id) {
        Session session = sessionFactory.openSession();
        org.hibernate.Transaction tr = session.beginTransaction();

        String sqlQuery =  "DELETE FROM product WHERE id='?'";
        Query query = session.createNativeQuery(sqlQuery);
        query.setParameter(1, id);

        int result = query.executeUpdate();
        System.out.println("Rows delete: " + result);
        tr.commit();
    }
    @Override
    @Transactional
    public void changeProductQuant(int idProduct, int changeQuantTo) {
        Session session = sessionFactory.openSession();
        org.hibernate.Transaction tr = session.beginTransaction();

        String sqlQuery =  "UPDATE product SET quant=quant-:changeQuantTo WHERE id=:idProduct";
        Query query = session.createNativeQuery(sqlQuery);
        query.setParameter("changeQuantTo", changeQuantTo);
        query.setParameter("idProduct", idProduct);

        int result = query.executeUpdate();
        System.out.println("Rows update: " + result);
        tr.commit();
        logger.info("Change quant of product with id="+idProduct+" to "+changeQuantTo+" elements");

    }
    @Override
    @Transactional
    public List <Product> searchProduct(String str) {
        String sqlQuery = "SELECT * FROM product\n" +
                "WHERE NAME LIKE \"%"+str+"%\" OR description LIKE \"%"+str+"%\"\n" +
                "UNION\n" +
                "SELECT product.id,product.name,description,photo,category,price,quant,seller\n" +
                "FROM product\n" +
                "JOIN seller ON product.seller=seller.id\n" +
                "WHERE seller.firstname LIKE \"%"+str+"%\" OR seller.surname LIKE \"%"+str+"%\"";
        Session session = sessionFactory.openSession();
        org.hibernate.Transaction tr = session.beginTransaction();

        List<Product> products=session.createNativeQuery(sqlQuery)
               .setResultTransformer(Transformers.aliasToBean(Product.class)).list();

        tr.commit();

        logger.info("Search of product by str=("+str+")");

        return products;
    }
    @Override
    @Transactional
    public List<Product> getProductsFromPurchaseSale() {
        int idBuyer=new DataService().getCurrentUser().getId();
        //int idBuyer=(int)user.getId();
        String sqlQuery = "SELECT product.id, product.name,product.description,\n" +
                "    product.photo, product.category, product.price,\n" +
                "    product.quant, product.seller FROM product\n" +
                "    JOIN purchase_sale ON product.id=purchase_sale.product_id\n" +
                "    WHERE buyer=:idBuyer AND purchase_sale.old=0" ;

        Session session = sessionFactory.openSession();
        org.hibernate.Transaction tr = session.beginTransaction();
        List <Product> products=session.createNativeQuery(sqlQuery).setParameter("idBuyer", idBuyer)
                .setResultTransformer(Transformers.aliasToBean(Product.class)).list();
        //if(!products.isEmpty()){}
        tr.commit();

        return products;
    }


    }
