package com.RGR.Auction.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.RGR.Auction.Service.Category.CategoryService;
import com.RGR.Auction.models.CategoryModel;
import com.RGR.Auction.repositories.CategoryRepositories;

@Controller
public class CategoryController {
	@Autowired
    private CategoryRepositories categoryRepository;
 @Autowired
    CategoryService categoryService;

 @GetMapping("/categories")
    public String getAllCategories(Model model) {
	 
	 Iterable <CategoryModel> categories=categoryRepository.findAll();
	 
     model.addAttribute("categories",categories);
    return "categories";
 }

}
