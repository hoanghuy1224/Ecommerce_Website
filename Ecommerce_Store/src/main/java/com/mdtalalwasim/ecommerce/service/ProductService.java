package com.mdtalalwasim.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.mdtalalwasim.ecommerce.entity.Category;
import com.mdtalalwasim.ecommerce.entity.Product;

public interface ProductService {

	public Product saveProduct(Product product);

	//boolean existCategory(String categoryName);

	public List<Product> getAllProducts();

	public Boolean deleteProduct(long id);

	public Optional<Product> findById(long id);

	public Product getProductById(long id);
	
	public Product updateProductById(Product product, MultipartFile file);
	
	List<Product> findAllActiveProducts(String category);
}
