package com.mdtalalwasim.ecommerce.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mdtalalwasim.ecommerce.entity.User;
import com.mdtalalwasim.ecommerce.repository.UserRepository;
import com.mdtalalwasim.ecommerce.service.UserService;
import com.mdtalalwasim.ecommerce.utils.AppConstant;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@Override
	public User saveUser(User user) {
		System.out.println("user obje :"+user.toString());
		user.setRole("ROLE_USER");
		user.setIsEnable(true);
		user.setAccountStatusNonLocked(true);
		user.setAccountfailedAttemptCount(0);
		user.setAccountLockTime(null);
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		try {
			User saveUser = userRepository.save(user);

			return saveUser;
		} catch (Exception e) {
			throw new RuntimeException("Failed to create user", e);
		}
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> getAllUsersByRole(String role) {
		// TODO Auto-generated method stub
		return userRepository.findByRole(role);
	}

	@Override
	public Boolean updateUserStatus(Boolean status, Long id) {
		Optional<User> userById = userRepository.findById(id);
		if (userById.isPresent()) {
			User user = userById.get();
			user.setIsEnable(status);
			userRepository.save(user);

			return true;
		} else {
			return false;
		}

	}

	@Override
	public void userFailedAttemptIncrease(User user) {
		int userAttempt= user.getAccountfailedAttemptCount()+1;
		user.setAccountfailedAttemptCount(userAttempt);
		userRepository.save(user);

	}

	@Override
	public void userAccountLock(User user) {
		user.setAccountStatusNonLocked(false);
		user.setAccountLockTime(new Date());
		userRepository.save(user);
	}

	@Override
	public boolean isUnlockAccountTimeExpired(User user) {
		long accountLockTime = user.getAccountLockTime().getTime();
		System.out.println("Account LockTime: "+accountLockTime);
		long  accountUnlockTime = accountLockTime+ AppConstant.UNLOCK_DURATION_TIME;
		System.out.println("Account Unlock Time :"+accountUnlockTime);
		
		long currentTime = System.currentTimeMillis();
		System.out.println("currentTime :"+currentTime);
		
		if(accountUnlockTime < currentTime) {
			user.setAccountStatusNonLocked(true);
			user.setAccountfailedAttemptCount(0);
			user.setAccountLockTime(null);
			userRepository.save(user);
			return true;
		}
		
		return false;
	}

	@Override
	public void userFailedAttempt(int userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUserResetTokenForSendingEmail(String email, String resetToken) {
		User user = userRepository.findByEmail(email);
		user.setResetTokens(resetToken);
		userRepository.save(user);
		
	}

	@Override
	public User getUserByresetTokens(String token) {
		// TODO Auto-generated method stub
		return userRepository.findByResetTokens(token);
	}

	@Override
	public User updateUserWhileResetingPassword(User userByToken) {
		// TODO Auto-generated method stub
		return userRepository.save(userByToken);
	}
	
	

}


