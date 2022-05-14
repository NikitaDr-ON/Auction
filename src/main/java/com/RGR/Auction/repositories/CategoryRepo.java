package com.RGR.Auction.repositories;

import com.RGR.Auction.models.CategoryModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends CrudRepository<CategoryModel, Long> {
    CategoryModel findByName(String name);
}
