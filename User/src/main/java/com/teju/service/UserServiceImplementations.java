package com.teju.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teju.config.JwtProvider;
import com.teju.model.User;
import com.teju.repository.UserRepository;

@Service
public class UserServiceImplementations implements UserService{
	
	@Autowired
	UserRepository ur;

	@Override
	public User getuserProfile(String jwt) {
		String username=JwtProvider.getEmailFromJwtToken(jwt);
		User u=ur.findbyEmail(username);
		return u;
		
	}
	
	
	

}
