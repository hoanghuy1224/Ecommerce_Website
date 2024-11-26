package com.mdtalalwasim.ecommerce.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.mdtalalwasim.ecommerce.entity.Cart;
import com.mdtalalwasim.ecommerce.entity.Product;
import com.mdtalalwasim.ecommerce.entity.User;
import com.mdtalalwasim.ecommerce.repository.CartRepository;
import com.mdtalalwasim.ecommerce.repository.ProductRepository;
import com.mdtalalwasim.ecommerce.repository.UserRepository;
import com.mdtalalwasim.ecommerce.service.CartService;
import com.mdtalalwasim.ecommerce.service.UserService;
import com.mdtalalwasim.ecommerce.utils.AppConstant;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Cart saveCart(Long productId, Long userId) {
		
		User user = userRepository.findById(userId).get();
		Product product = productRepository.findById(productId).get();
		
		Cart cartStatus = cartRepository.findByProductIdAndUserId(productId, userId);
		
		Cart cart = null;
		
		if(ObjectUtils.isEmpty(cartStatus)) {
			//cart Is empty - so add product to Cart
			cart = new Cart();
			cart.setUser(user);
			cart.setProduct(product);
			cart.setQuantity(1);
			cart.setTotalPrice(1 * product.getDiscountPrice());
		}else {
			//cart Is holding product. so increases the quantity of the existing product.
			cart =cartStatus;
			cart.setQuantity(cart.getQuantity()+1);
			cart.setTotalPrice(cart.getQuantity() * cart.getProduct().getDiscountPrice());
		}
		Cart saveCart = cartRepository.save(cart);
		
		return saveCart;
	}

	@Override
	public List<Cart> getCartsByUser(Long userId) {
	 List<Cart> carts = cartRepository.findByUserId(userId);
	 //System.out.println("CARTS :"+carts.toString());
	 
	 Double totalOrderPrice = 0.0;
	 
		// because of my totalPrice colum is transient, so we dont get totalPrice
		// directly from database table.
		// we need to fetch it
	 List<Cart> updatedCartList = new ArrayList<>();
		for (Cart cart : carts) {
			Double totalPrice = (cart.getProduct().getDiscountPrice() * cart.getQuantity());
			cart.setTotalPrice(totalPrice);
			System.out.println("totalPrice is :"+totalPrice);
			
			totalOrderPrice = totalOrderPrice + totalPrice;
			
			cart.setTotalOrderPrice(totalOrderPrice);
			System.out.println("totalOrderPrice is :"+totalOrderPrice);
			updatedCartList.add(cart);
		}
		
	 
	 
		return updatedCartList;
	}

	@Override
	public Long getCounterCart(Long userId) {
		Long cartCountByUserId = cartRepository.countByUserId(userId);
		return cartCountByUserId;
	}

	@Override
	public Boolean updateCartQuantity(String symbol, Long cartId) {
		
		Optional<Cart> cart = cartRepository.findById(cartId);
		int quantity;
		if(cart.isPresent()) {
			if(symbol.equalsIgnoreCase("decrease")) {
				Integer dbQty = cart.get().getQuantity();
				quantity	= dbQty - 1;
				if(quantity <= 0) {
					cartRepository.deleteById(cartId);
					return true;
				}
				
			}else {
				Integer dbQty = cart.get().getQuantity();
				quantity	= dbQty + 1;
			}
			cart.get().setQuantity(quantity);
			cartRepository.save(cart.get());
		}
		
		
		
		return false;
	}

}


