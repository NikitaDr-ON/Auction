package com.RGR.Auction.Service.TypeDelivery;

import com.RGR.Auction.models.ServiceModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class TypeDeliveryServiceImpl implements TypeDeliveryService{
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    @Override
    @Transactional
    public ServiceModel getServiceById(int id) {
        String sqlQuery = "SELECT * FROM service WHERE id=:id" ;
        Session session = sessionFactory.openSession();
        org.hibernate.Transaction tr = session.beginTransaction();

        List<ServiceModel> services=session.createNativeQuery(sqlQuery).setParameter("id", id)
                .setResultTransformer(Transformers.aliasToBean(ServiceModel.class)).list();
        //if(!products.isEmpty()){}
        tr.commit();
        return services.get(0);
    }
    @Override
    @Transactional
    public List<ServiceModel> getAllServices() {
        String sqlQuery = "SELECT * FROM service" ;
        Session session = sessionFactory.openSession();
        org.hibernate.Transaction tr = session.beginTransaction();

        List<ServiceModel> services=session.createNativeQuery(sqlQuery)
                .setResultTransformer(Transformers.aliasToBean(ServiceModel.class)).list();
        //if(!products.isEmpty()){}
        tr.commit();
        return services;
    }
}
