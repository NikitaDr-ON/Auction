package com.RGR.Auction.Service.Category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.RGR.Auction.models.CategoryModel;
import com.RGR.Auction.repositories.CategoryRepositories;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepositories categoryRepository;
	@Override
	public List<CategoryModel> getAll() {
		 return categoryRepository.findAllByOrderByIdDesc();
	}

	@Override
	public CategoryModel getById(long id) throws NotFoundException {
	     CategoryModel category = categoryRepository.findById(id);
	     if (category == null) {
	         throw new NotFoundException();
	         }
	     return category;
	}

}
