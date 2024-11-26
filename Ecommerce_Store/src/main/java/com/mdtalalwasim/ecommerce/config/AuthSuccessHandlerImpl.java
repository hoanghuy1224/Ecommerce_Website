package com.mdtalalwasim.ecommerce.config;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthSuccessHandlerImpl implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
			
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		
		//this will provides OUR ROLES
		Set<String> roles = AuthorityUtils.authorityListToSet(authorities);
		System.out.println("ROLES :"+roles.toString());
		if(roles.contains("ROLE_ADMIN")) {
			response.sendRedirect("/admin/");
		}else {//else by default ROLE_USER//currently we have only two ROLES USER and ADMIN
			response.sendRedirect("/");
		}
	}
	

}
