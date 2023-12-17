package com.RGR.Auction.Service.Seller;

import com.RGR.Auction.models.Seller;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class SellerServiceImpl implements SellerService {
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    @Override
    @Transactional
    public Seller getSellerById(int id) {
        String sqlQuery = "SELECT * FROM seller WHERE id=:id" ;
        Session session = sessionFactory.openSession();
        org.hibernate.Transaction tr = session.beginTransaction();

        List<Seller> seller=session.createNativeQuery(sqlQuery).setParameter("id", id)
                .setResultTransformer(Transformers.aliasToBean(Seller.class)).list();
       tr.commit();
        return seller.get(0);
    }
    @Override
    @Transactional
    public List<Seller> getAllSeller() {
        String sqlQuery = "SELECT * FROM seller" ;
        Session session = sessionFactory.openSession();
        org.hibernate.Transaction tr = session.beginTransaction();

        List <Seller> sellers=session.createNativeQuery(sqlQuery)
                .setResultTransformer(Transformers.aliasToBean(Seller.class)).list();
        tr.commit();
        return sellers;
    }
}
