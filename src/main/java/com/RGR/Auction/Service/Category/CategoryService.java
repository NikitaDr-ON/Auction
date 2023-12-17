package com.RGR.Auction.Service.Category;

import java.util.List;

import com.RGR.Auction.models.Category;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {

	Category getCategoryById(int id);
	List<Category> getAllCategories();
}
