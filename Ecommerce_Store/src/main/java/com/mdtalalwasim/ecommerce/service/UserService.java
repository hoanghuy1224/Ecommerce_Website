package com.mdtalalwasim.ecommerce.service;

import java.util.List;

import com.mdtalalwasim.ecommerce.entity.User;

public interface UserService {
	
	public User saveUser(User user);
	
	public User getUserByEmail(String email);
	
	public List<User> getAllUsersByRole(String role);

	public Boolean updateUserStatus(Boolean status, Long id);
	
	//lock user account for attempting wrong credentials
	public void userFailedAttemptIncrease(User user);
	
	public void userAccountLock(User user);
	
	public boolean isUnlockAccountTimeExpired(User user);
	
	public void userFailedAttempt(int userId);

	public void updateUserResetTokenForSendingEmail(String email, String resetToken);

	public User getUserByresetTokens(String token);

	public User updateUserWhileResetingPassword(User userByToken);

	
	
}
