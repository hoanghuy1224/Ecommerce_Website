package com.mdtalalwasim.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mdtalalwasim.ecommerce.entity.User;
import com.mdtalalwasim.ecommerce.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService{

	

 @Autowired UserRepository userRepository;

	/*
	 * private final UserRepository userRepository;
	 * 
	 * @Autowired public UserDetailsServiceImpl(UserRepository userRepository) {
	 * this.userRepository = userRepository; }
	 */
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("username "+ username);
		User user = userRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("User NOT Found for :"+username);
		}
		return new CustomUser(user);	
	}

}
