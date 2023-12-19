package com.RGR.Auction.Service.PurchaseSale;

import java.util.List;

import com.RGR.Auction.Service.DataService;
import com.RGR.Auction.Service.Product.ProductService;
import com.RGR.Auction.models.Data;
import com.RGR.Auction.models.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RGR.Auction.models.PurchaseSale;

import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseSaleServiceImpl implements PurchaseSaleService{
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private static final Logger logger = LogManager.getLogger(PurchaseSaleServiceImpl.class);


    @Autowired
    ProductService products;


    @Override
    @Transactional
    public PurchaseSale getPurchaseSaleById(int id) {
        String sqlQuery = "SELECT id_sale,buyer,product_id,purchase_price,purchase_quant FROM purchase_sale WHERE id_sale=:id" ;
        Session session = sessionFactory.openSession();
        org.hibernate.Transaction tr = session.beginTransaction();

        List<PurchaseSale> purchaseSale=session.createNativeQuery(sqlQuery).setParameter("id", id)
                .setResultTransformer(Transformers.aliasToBean(PurchaseSale.class)).list();
        tr.commit();
        return purchaseSale.get(0);
    }
    @Override
    @Transactional
    public List<PurchaseSale> getAllPurchaseSale() {
        String sqlQuery = "SELECT id_sale,buyer,product_id,purchase_price,purchase_quant FROM purchase_sale WHERE old=0" ;
        Session session = sessionFactory.openSession();
        org.hibernate.Transaction tr = session.beginTransaction();

        List<PurchaseSale> purchaseSales=session.createNativeQuery(sqlQuery)
                .setResultTransformer(Transformers.aliasToBean(PurchaseSale.class)).list();
        tr.commit();
        return purchaseSales;
    }
    @Override
    @Transactional
    public List<PurchaseSale> getAllPurchaseSaleByBuyer() {
        int idBuyer=(int)new DataService().getCurrentUser().getId();
        //int idBuyer=(int)user.getId();
        String sqlQuery = "SELECT id_sale,buyer,product_id,purchase_price,purchase_quant" +
                " FROM purchase_sale WHERE buyer=:idBuyer AND old=0" ;
        Session session = sessionFactory.openSession();
        org.hibernate.Transaction tr = session.beginTransaction();

        List<PurchaseSale> purchaseSales=session.createNativeQuery(sqlQuery).setParameter("idBuyer", idBuyer)
                .setResultTransformer(Transformers.aliasToBean(PurchaseSale.class)).list();
        tr.commit();


        return purchaseSales;
    }

    @Override
    @Transactional
    public void addPurchaseSale(Data user, Product product, int purchaseQuant) {
        //int userId=new DataService().getCurrentUser().getId();
        int userId=(int)user.getId();

        int idProduct=product.getId();
        List <PurchaseSale> existPurchaseSale= getAllPurchaseSale();
        boolean flag=false;
        int idExistPurchaseSale=0;
        for(PurchaseSale ps:existPurchaseSale){
            if((ps.getProduct_id() == idProduct) && (ps.getBuyer() == userId)){
                flag=true;
                idExistPurchaseSale=ps.getId_sale();
            }
        }

        int purchasePrice=product.getPrice();
        int quantOfProd= product.getQuant();

        if (quantOfProd>=purchaseQuant) {
            if(flag==false){
            String sqlQuery =  "INSERT INTO purchase_sale(buyer,product_id,purchase_price,purchase_quant) VALUE(" +
                    ":userId," +
                    ":idProduct," +
                    ":purchasePrice," +
                    ":purchaseQuant)";
            Session session = sessionFactory.openSession();
            org.hibernate.Transaction tr = session.beginTransaction();

            Query query = session.createNativeQuery(sqlQuery);
            query.setParameter("userId", userId);
            query.setParameter("idProduct", idProduct);
            query.setParameter("purchasePrice", purchasePrice);
            query.setParameter("purchaseQuant", purchaseQuant);
            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);

            logger.info("Product with id="+idProduct+" add to basket(purchase_sale) by user with id="+userId);

            tr.commit();
            }else{
                String sqlQuery =  "UPDATE purchase_sale SET " +
                        "purchase_quant=:purchaseQuant, purchase_price=:purchasePrice " +
                        "WHERE id_sale=:idSale";
                Session session = sessionFactory.openSession();
                org.hibernate.Transaction tr = session.beginTransaction();

                Query query = session.createNativeQuery(sqlQuery);

                query.setParameter("purchaseQuant", purchaseQuant);
                query.setParameter("purchasePrice", purchasePrice);
                query.setParameter("idSale", idExistPurchaseSale);
                int result = query.executeUpdate();
                System.out.println("Rows update: " + result);

                logger.info("Quant of product with id="+idProduct+" update in basket(purchase_sale) by user with id="+userId);

                tr.commit();
                }
        }
        else System.out.println("Товаров недостаточно");
    }

    @Override
    @Transactional
    public void deletePurchaseSale(int idSale) {
        Session session = sessionFactory.openSession();
        org.hibernate.Transaction tr = session.beginTransaction();

        String sqlQuery =  "DELETE FROM purchase_sale WHERE id_sale=:idSale";
        Query query = session.createNativeQuery(sqlQuery);
        query.setParameter("idSale",idSale);

        int result = query.executeUpdate();
        System.out.println("Rows delete: " + result);
        logger.info("Entry from the table of basket(purchase_sale) with id="+idSale+" delete");
        tr.commit();
    }
    @Override
    @Transactional
    public void checkOldPurchaseSale(int idSale) {
        Session session = sessionFactory.openSession();
        org.hibernate.Transaction tr = session.beginTransaction();

        String sqlQuery =  "UPDATE purchase_sale SET old=\"1\" WHERE id_sale=:idSale";
        Query query = session.createNativeQuery(sqlQuery);
        query.setParameter("idSale",idSale);

        int result = query.executeUpdate();
        System.out.println("Rows delete: " + result);
        logger.info("Entry from the table of basket(purchase_sale) with id="+idSale+" update to status OLD (order was send to user)");

        tr.commit();
    }


}
