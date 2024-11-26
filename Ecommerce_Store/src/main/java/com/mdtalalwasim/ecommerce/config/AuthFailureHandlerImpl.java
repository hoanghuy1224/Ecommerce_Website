package com.mdtalalwasim.ecommerce.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import com.mdtalalwasim.ecommerce.entity.User;
import com.mdtalalwasim.ecommerce.repository.UserRepository;
import com.mdtalalwasim.ecommerce.service.UserService;
import com.mdtalalwasim.ecommerce.utils.AppConstant;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class AuthFailureHandlerImpl extends SimpleUrlAuthenticationFailureHandler {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String email = request.getParameter("username");
		User user = userRepository.findByEmail(email);
		
		if(user != null) {
			if(user.getIsEnable()) {
				//account is active
				
				if(user.getAccountStatusNonLocked()) {
					//Non-locked / Unlocked
					if(user.getAccountfailedAttemptCount() < AppConstant.ATTEMPT_COUNT) {
						userService.userFailedAttemptIncrease(user);
					}else {
						//
						userService.userAccountLock(user);
						exception = new LockedException("Your account is Locked! Failed Attempt 3");
					}
					
				}else {
					//Locked
					if(userService.isUnlockAccountTimeExpired(user)) {
						exception = new LockedException("Your account is UnLocked, Now you can login to system");
					}else {
						exception = new LockedException("Your account is Locked! Please try after sometimes");
					}
				}
				
			}else {
				//account is inactive
				exception = new LockedException("Your account is inactive");
			}
		}else {
			exception = new LockedException("Email & Password Invalid!");
		}
		
		super.setDefaultFailureUrl("/signin?error");
		super.onAuthenticationFailure(request, response, exception);
	}
	
	
}
