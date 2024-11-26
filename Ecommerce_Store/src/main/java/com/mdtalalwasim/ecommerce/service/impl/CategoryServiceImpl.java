package com.mdtalalwasim.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.mdtalalwasim.ecommerce.entity.Category;
import com.mdtalalwasim.ecommerce.repository.CategoryRepository;
import com.mdtalalwasim.ecommerce.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category saveCategory(Category category) {
		// TODO Auto-generated method stub
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

	@Override
	public boolean existCategory(String categoryName) {
		// TODO Auto-generated method stub
		return categoryRepository.existsByCategoryName(categoryName);
	}

	@Override
	public Boolean deleteCategory(long id) {
		// TODO Auto-generated method stub
		
		Category categoryFound = categoryRepository.findById(id).orElse(null);
		
		if(!ObjectUtils.isEmpty(categoryFound)) {
			categoryRepository.delete(categoryFound);
			return true;
		}
		
		return false;
	}

	@Override
	public Optional<Category> findById(long id) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id);
	}

	@Override
	public List<Category> findAllActiveCategory() {
		List<Category> categories = categoryRepository.findByIsActiveTrue();
		return categories;
	}

}
