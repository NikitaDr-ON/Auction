package com.RGR.Auction.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.RGR.Auction.models.CategoryModel;

@Repository
public interface CategoryRepositories extends CrudRepository<CategoryModel, Long>{
	
	CategoryModel findById(long id);
    List <CategoryModel> findAllByOrderByIdDesc();
	
}
