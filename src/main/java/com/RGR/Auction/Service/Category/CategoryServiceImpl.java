package com.RGR.Auction.Service.Category;

import java.util.List;

import com.RGR.Auction.models.Category;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Service
public class CategoryServiceImpl implements CategoryService{

	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	@Override
	@Transactional
	public Category getCategoryById(int id) {
		String sqlQuery = "SELECT * FROM category WHERE id=:id" ;
		Session session = sessionFactory.openSession();
		org.hibernate.Transaction tr = session.beginTransaction();

		List<Category> categories=session.createNativeQuery(sqlQuery).setParameter("id", id)
				.setResultTransformer(Transformers.aliasToBean(Category.class)).list();

		tr.commit();
		return categories.get(0);
	}
	@Override
	@Transactional
	public List<Category> getAllCategories() {
		String sqlQuery = "SELECT * FROM category" ;
		Session session = sessionFactory.openSession();
		org.hibernate.Transaction tr = session.beginTransaction();

		List<Category> categories=session.createNativeQuery(sqlQuery)
				.setResultTransformer(Transformers.aliasToBean(Category.class)).list();

		tr.commit();
		return categories;
	}
}
