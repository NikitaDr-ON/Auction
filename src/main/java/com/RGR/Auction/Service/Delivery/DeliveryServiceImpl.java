package com.RGR.Auction.Service.Delivery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.RGR.Auction.Service.MailSender;
import com.RGR.Auction.Service.Product.ProductService;
import com.RGR.Auction.Service.PurchaseSale.PurchaseSaleService;
import com.RGR.Auction.Service.Seller.SellerService;
import com.RGR.Auction.Service.TypeDelivery.TypeDeliveryService;
import com.RGR.Auction.models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class DeliveryServiceImpl implements DeliveryService {

	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	@Autowired
	MailSender mailSender;
	@Autowired
	ProductService products;
	@Autowired
	PurchaseSaleService purchaseSale;
	@Autowired
	SellerService sellerObj;
	@Autowired
	TypeDeliveryService servicesServ;
	@Override
	@Transactional
	public void addDelivery(int id_sale, int service) {
		int isSent=0;
		Session session = sessionFactory.openSession();
		org.hibernate.Transaction tr = session.beginTransaction();

		Date date_delivery=new Date();
		String sqlQuery =  "INSERT INTO delivery(id_sale,isSent,service,date_delivery) " +
				"VALUES (:id_sale,:isSent,:service,:date_delivery)";
		Query query = session.createNativeQuery(sqlQuery);
		query.setParameter("id_sale", id_sale);
		query.setParameter("isSent", isSent);
		query.setParameter("service", service);
		query.setParameter("date_delivery", date_delivery);

		int result = query.executeUpdate();
		tr.commit();
		System.out.println("Rows affected: " + result);

	}
	@Override
	@Transactional
	public void payDelivery(Data user) {
		List<Delivery>deliveriesForPay =getActiveDeliveriesByUser(user);
		List<PurchaseSale> sales=new ArrayList<>();
		ServiceModel service=servicesServ.getServiceById(deliveriesForPay.get(0).getService());

		for (Delivery deliv: deliveriesForPay){
			sales.add(purchaseSale.getPurchaseSaleById(deliv.getId_sale()));
		}

		int isSent=0;
		//User user=new DataService().getCurrentUser();
		UUID uuid = UUID.randomUUID();

		String message="Товары были отправлены на адрес "+user.getAddress()+" сервисом " +service.getName()+
				"\n Воспользуйтесь трек-номером для отслеживания посылки: "+uuid.toString();

		if(!StringUtils.isEmpty(user.getMail())) {
			mailSender.send(user.getMail(), "Интернет-магазин \"Rarity\"", message);
			isSent = 1;

			for(int i=0;i<sales.size();i++) {
				String sqlQuery = "UPDATE delivery " +
						"SET isSent=:isSent WHERE id_sale=:idSale";
				Session session = sessionFactory.openSession();
				org.hibernate.Transaction tr = session.beginTransaction();

				Query query = session.createNativeQuery(sqlQuery);
				query.setParameter("isSent", isSent);
				query.setParameter("idSale", sales.get(i).getId_sale());
				int result = query.executeUpdate();
				System.out.println("Row affected: "+result);
				tr.commit();

				//Изменение количества товаров в таблице БД Product после покупки одного пользователя
				int quant = sales.get(i).getPurchase_quant();
				int idProduct = sales.get(i).getProduct_id();
				products.changeProductQuant(idProduct, quant);
				purchaseSale.checkOldPurchaseSale(sales.get(i).getId_sale());
			}




			//Оповещение продавца о том, что его товар был куплен
			/*
			int idSeller=products.getProductById(idProduct).getSeller();
			String mailSeller=sellerObj.getSellerById(idSeller).getEmail();
			String cardSeller=sellerObj.getSellerById(idSeller).getCardinfo();
			String messageToSeller="Your product has been sold. Payment will be credited to the account: " +
					cardSeller +" within 7 days";

			mailSender.send(mailSeller, "Massage from the store \"Rarity\"", messageToSeller);
			 */
		}
	}

	@Override
	@Transactional
	public List<Delivery> getDeliveriesByUser(Data user) {
		//int userId=new DataService().getCurrentUser().getId();
		int userId=(int)user.getId();

		String sqlQuery = "SELECT  delivery_id,delivery.id_sale,isSent,service,date_delivery FROM delivery " +
				"JOIN purchase_sale ON delivery.id_sale=purchase_sale.id_sale " +
				"WHERE buyer=:buyer" ;
		Session session = sessionFactory.openSession();
		org.hibernate.Transaction tr = session.beginTransaction();

		List<Delivery> deliveries=session.createNativeQuery(sqlQuery).setParameter("buyer", userId)
				.setResultTransformer(Transformers.aliasToBean(Delivery.class)).list();
		tr.commit();
		return deliveries;
	}
	@Override
	@Transactional
	public List<Delivery> getActiveDeliveriesByUser(Data user) {
		//int userId=new DataService().getCurrentUser().getId();
		int userId=(int)user.getId();

		String sqlQuery = "SELECT  delivery_id,delivery.id_sale,isSent,service,date_delivery FROM delivery " +
				"JOIN purchase_sale ON delivery.id_sale=purchase_sale.id_sale " +
				"WHERE buyer=:buyer AND isSent=0" ;
		Session session = sessionFactory.openSession();
		org.hibernate.Transaction tr = session.beginTransaction();

		List<Delivery> deliveries=session.createNativeQuery(sqlQuery).setParameter("buyer", userId)
				.setResultTransformer(Transformers.aliasToBean(Delivery.class)).list();
		tr.commit();
		return deliveries;
	}
	@Override
	@Transactional
	public List<Delivery> deleteActiveDeliveriesByUser(Data user) {

		//int userId=new DataService().getCurrentUser().getId();
		int userId=(int)user.getId();
		String sqlQuery = "DELETE from delivery WHERE delivery_id=ANY(" +
				"SELECT delivery_id FROM delivery " +
				"JOIN purchase_sale ON delivery.id_sale=purchase_sale.id_sale " +
				"WHERE buyer=:buyer) AND isSent=0" ;
		Session session = sessionFactory.openSession();
		org.hibernate.Transaction tr = session.beginTransaction();

		List<Delivery> deliveries=session.createNativeQuery(sqlQuery).setParameter("buyer", userId)
				.setResultTransformer(Transformers.aliasToBean(Delivery.class)).list();
		tr.commit();
		return deliveries;
	}
}