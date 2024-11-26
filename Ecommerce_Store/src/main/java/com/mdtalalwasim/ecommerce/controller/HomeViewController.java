package com.mdtalalwasim.ecommerce.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mdtalalwasim.ecommerce.entity.Category;
import com.mdtalalwasim.ecommerce.entity.Product;
import com.mdtalalwasim.ecommerce.entity.User;
import com.mdtalalwasim.ecommerce.service.CartService;
import com.mdtalalwasim.ecommerce.service.CategoryService;
import com.mdtalalwasim.ecommerce.service.ProductService;
import com.mdtalalwasim.ecommerce.service.UserService;
import com.mdtalalwasim.ecommerce.utils.CommonUtils;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeViewController {
	//without login
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	CartService cartService;
	
//	@Autowired
//	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//to track which user is login right Now
	//by default call this method when any request come to this controller because of @ModelAttribut
	@ModelAttribute 
	public void getUserDetails(Principal principal, Model model) {
		if(principal != null) {
			String currenLoggedInUserEmail = principal.getName();
			User currentUserDetails = userService.getUserByEmail(currenLoggedInUserEmail);
			//System.out.println("Current Logged In User is :: HOME Controller :: "+currentUserDetails.toString());
			model.addAttribute("currentLoggedInUserDetails",currentUserDetails);
			
			//for showing user cart count
			Long countCartForUser = cartService.getCounterCart(currentUserDetails.getId());
			System.out.println("HomeControll Cart Count :"+countCartForUser);
			model.addAttribute("countCartForUser", countCartForUser);
			
		}
		
		List<Category> allActiveCategory = categoryService.findAllActiveCategory();
		model.addAttribute("allActiveCategory",allActiveCategory);
		
	}
	
	@GetMapping("/")
	public String homeIndex(Model model) {
		
		List<Category> allActiveCategory = categoryService.findAllActiveCategory();
		List<Category> latestSixActiveCategory = allActiveCategory.stream()
				.sorted((cat1, cat2)->cat2.getId().compareTo(cat1.getId()))
				.limit(6).toList();
		
		List<Product> latestEightActiveProducts = productService.findAllActiveProducts("").stream()
				.sorted((p1,p2)->p2.getId().compareTo(p1.getId()))
				.limit(8).toList();
		
		model.addAttribute("latestEightActiveProducts",latestEightActiveProducts);
		model.addAttribute("latestSixActiveCategory",latestSixActiveCategory);
		return "index.html";
	}
	
	@GetMapping("/signin")
	public String login() {
		
		return "login";
	}
	
	@GetMapping("/register")
	public String register() {
		
		return "register";
	}
	
	@GetMapping("/products")
	public String product(Model model, @RequestParam(name= "category", defaultValue = "") String category) {
		//System.out.println("Category="+category);
		
		List<Category> allActiveCategory = categoryService.findAllActiveCategory();
		List<Product> allActiveProducts = productService.findAllActiveProducts(category);
		model.addAttribute("allActiveCategory", allActiveCategory);
		model.addAttribute("allActiveProducts", allActiveProducts);
		model.addAttribute("paramValue", category);
		return "product";
	}	
	
	@GetMapping("/product/{id}")
	public String viewProduct(@PathVariable long id, Model model) 
	{
		Product productById = productService.getProductById(id);
		model.addAttribute("product",productById);
		return "view-product";
	}
	
	@PostMapping("/save-user")
	public String saveUserDetails(@ModelAttribute User user, @RequestParam("file") MultipartFile file, Model model, HttpSession session) throws IOException 
	{
		
		String profileImage = file.isEmpty() ? "default.jpg" : file.getOriginalFilename();
		user.setProfileImage(profileImage);
		
		User saveUser = userService.saveUser(user);
		
		if(!ObjectUtils.isEmpty(saveUser)) 
		{
			if(!file.isEmpty()) 
			{
				//get path to static/img directory
				File saveFile =new ClassPathResource("static/img").getFile();
				System.out.println("SaveFile is: "+saveFile);
				
				//full-path
				Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+"profile_img"+File.separator+file.getOriginalFilename());
				System.out.println("Path for Profile Image :"+path);
				
				//now
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			}
			session.setAttribute("successMsg", "User Registered Successfully");
			
		}else {
			session.setAttribute("errorMsg", "Something wrong on server!");
		}
		//model.addAttribute("product",productById);
		return "redirect:/register";
	}
	
	
	//forgot Password
	
	@GetMapping("/forgot-password")
	public String forgotPassword() 
	{
		
		return "forget-password";
	}
	
	
	@PostMapping("/forgot-password")
	public String forgetPasswordProcessing(@RequestParam String email, HttpSession session, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException 
	{
		User user = userService.getUserByEmail(email);
		if(!ObjectUtils.isEmpty(user)) {
			
			String resetToken = UUID.randomUUID().toString();
			System.out.println("RESET TOKEN: "+resetToken);
			userService.updateUserResetTokenForSendingEmail(email, resetToken);
			
			
			//URL Like This : http://localhost:8080/reset-password?token=dfjdlkfjsldfdlfkdflkdfjdlk
			String url = CommonUtils.generateUrl(request)+"/reset-password?token="+resetToken;
			System.out.println("url :"+url);
			
			
			//Boolean isEmailSendToUser = CommonUtils.sendEmail(url, email);
			Boolean isEmailSendToUser = commonUtils.sendEmail(url, email);
			
			if(isEmailSendToUser == true) {
				session.setAttribute("successMsg", "Please check your email, Password Reset Link has been sent to your email.");
			}else {
				session.setAttribute("errorMsg", "Something wrong on server. Email Not Sent!");
			}
		
		}else {
			session.setAttribute("errorMsg", "Invalid Email");
		}
		return "redirect:/forgot-password";
	}
	
	//reset password
	@GetMapping("/reset-password")
	public String resetPassword(@RequestParam String token, HttpSession session, Model model) 
	{
		User userByToken = userService.getUserByresetTokens(token);
		if(ObjectUtils.isEmpty(userByToken)) {
			//session.setAttribute("errorMsg", "Invalid TOKEN");
			model.addAttribute("msg", "Your Link is invalid or expired!");
			return "message";
		}
		model.addAttribute("token", token);
		return "reset-password";
	}
	
	
//	@PostMapping("/reset-password")
//	public String resetPasswordOperation(@RequestParam String token, @RequestParam String password, @RequestParam String confirmPassword,  HttpSession session, Model model) 
//	{
//		System.out.println("Given Pass: "+password);
//		System.out.println("Given Pass2: "+confirmPassword);
//		
//		if(password == confirmPassword) {
//			User userByToken = userService.getUserByresetTokens(token);
//			if(ObjectUtils.isEmpty(userByToken)) {
//				model.addAttribute("msg", "Your Link is invalid or expired!");
//				return "message";
//			}else {
//				
//				//userByToken.setPassword(bCryptPasswordEncoder.encode(password));
//				userByToken.setPassword(passwordEncoder.encode(password));
//				userByToken.setResetTokens(null);
//				User updatedUser = userService.updateUserWhileResetingPassword(userByToken);//this method only update user's password and ResetTokens.
//				session.setAttribute("successMsg", "Password Changed Successfully");
//				model.addAttribute("msg", "Password Changed Successfully");
//				return "message";
//			}
//		}else {
//			model.addAttribute("msg", "Password Missmatch.");
//			return "message";
//		}
//		
//		
//		
//	}
	
	@PostMapping("/reset-password")
	public String resetPasswordOperation(@RequestParam String token, @RequestParam String password, HttpSession session, Model model) 
	{
		
		User userByToken = userService.getUserByresetTokens(token);
		if(ObjectUtils.isEmpty(userByToken)) {
			model.addAttribute("msg", "Your Link is invalid or expired!");
			return "message";
		}else {
			
			//userByToken.setPassword(bCryptPasswordEncoder.encode(password));
			userByToken.setPassword(passwordEncoder.encode(password));
			userByToken.setResetTokens(null);
			User updatedUser = userService.updateUserWhileResetingPassword(userByToken);//this method only update user's password and ResetTokens.
			session.setAttribute("successMsg", "Password Changed Successfully");
			model.addAttribute("msg", "Password Changed Successfully");
			return "message";
		}
		
	}
	
}
