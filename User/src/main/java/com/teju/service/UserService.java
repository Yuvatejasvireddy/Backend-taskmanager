package com.teju.service;

import org.springframework.stereotype.Service;

import com.teju.model.User;

@Service
public interface UserService {

	public User getuserProfile(String jwt);
	
	
}
