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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	ProductService productService;
	@Autowired
	PurchaseSaleService purchaseSale;
	@Autowired
	SellerService sellerObj;
	@Autowired
	TypeDeliveryService servicesServ;
	private static final Logger logger = LogManager.getLogger(DeliveryServiceImpl.class);

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

		logger.info("Добавили доставку с идентификатором корзины="+id_sale);


	}
	@Override
	@Transactional
	public void payDelivery(User user) {
		List<Delivery>deliveriesForPay =getActiveDeliveriesByUser(user);
		List<PurchaseSale> sales=new ArrayList<>();
		ServiceModel service=servicesServ.getServiceById(deliveriesForPay.get(0).getService());
		List <Product> products= productService.getAllProducts();
		List<Product> buyProducts=new ArrayList<>();


		for (Delivery deliv: deliveriesForPay){
			sales.add(purchaseSale.getPurchaseSaleById(deliv.getId_sale()));
		}

		int isSent=0;
		UUID uuid = UUID.randomUUID();

		String message= "Здравствуйте, "+user.getName()+", товары были отправлены на адрес "+user.getAddress()+" сервисом " +service.getName()+
				"\n Воспользуйтесь трек-номером для отслеживания посылки: "+uuid.toString()+
				"\n";

		if(!StringUtils.isEmpty(user.getMail())) {
			isSent = 1;
			mailSender.send(user.getMail(), "Интернет-магазин \"Rarity\"", message);

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
				productService.changeProductQuant(idProduct, quant);
				purchaseSale.checkOldPurchaseSale(sales.get(i).getId_sale());
				if (idProduct==products.get(i).getId()){
					buyProducts.add(products.get(i));
				}

				logger.info("Delivery with id_sale="+sales.get(i).getId_sale()+" was pay by user with id="+user.getId());
			}
			for(int k=0;k<buyProducts.size();k++) {

				int idSeller=buyProducts.get(k).getSeller();
				Seller seller=sellerObj.getSellerById(idSeller);
				String nameSeller=seller.getName();
				String mailSeller=seller.getEmail();
				String cardSeller=seller.getCardinfo();
				String messageToSeller="Здравствуйте, "+nameSeller+", товар был продан. Оплата поступит на счет: "+cardSeller+
						" сегодня или в течении 7 дней. \n Описание товара: "+buyProducts.get(k).getName()+
						" \n Описание: "+buyProducts.get(k).getDescription()+
						" \n Цена: "+ sales.get(k).getPurchase_price() + " \n Количество: "+ sales.get(k).getPurchase_quant()+"\n ";

				mailSender.send(mailSeller, "Интернет-магазин \"Rarity\"", messageToSeller);

			}

		}
	}

	@Override
	@Transactional
	public List<Delivery> getDeliveriesByUser(User user) {
		//int userId=new DataService().getCurrentUser().getId();
		int userId=(int)user.getId();

		String sqlQuery = "SELECT delivery_id,delivery.id_sale,isSent,service,date_delivery FROM delivery " +
				"JOIN purchase_sale ON delivery.id_sale=purchase_sale.id_sale " +
				"WHERE buyer=:buyer ORDER BY delivery.id_sale ASC" ;
		Session session = sessionFactory.openSession();
		org.hibernate.Transaction tr = session.beginTransaction();

		List<Delivery> deliveries=session.createNativeQuery(sqlQuery).setParameter("buyer", userId)
				.setResultTransformer(Transformers.aliasToBean(Delivery.class)).list();
		tr.commit();
		return deliveries;
	}
	@Override
	@Transactional
	public List<Delivery> getActiveDeliveriesByUser(User user) {
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
	public List<Delivery> deleteActiveDeliveriesByUser(User user) {

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
		logger.info("Пользователь с id="+user.getId()+" отменил оплату заказа из корзины(purchase_sale)");

		return deliveries;
	}
	@Override
	@Transactional
	public List<Delivery> getAllDeliveries() {

		String sqlQuery = "SELECT  delivery_id,delivery.id_sale,isSent,service,date_delivery FROM delivery" +
				" ORDER BY id_sale ASC";
		Session session = sessionFactory.openSession();
		org.hibernate.Transaction tr = session.beginTransaction();

		List<Delivery> deliveries=session.createNativeQuery(sqlQuery)
				.setResultTransformer(Transformers.aliasToBean(Delivery.class)).list();
		tr.commit();
		return deliveries;
	}

}