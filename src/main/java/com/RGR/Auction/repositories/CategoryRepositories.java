package com.RGR.Auction.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.RGR.Auction.models.CategoryModel;

@Repository
public interface CategoryRepositories extends CrudRepository<CategoryModel, Integer>{
	
	CategoryModel findById(Long id);
    List <CategoryModel> findAllByOrderByIdDesc();
	
}
