package com.RGR.Auction.Service.Category;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.RGR.Auction.models.CategoryModel;

@Service
public interface CategoryService {

	List<CategoryModel> getAll();
	CategoryModel getById(Long id) throws NotFoundException;
 

}
