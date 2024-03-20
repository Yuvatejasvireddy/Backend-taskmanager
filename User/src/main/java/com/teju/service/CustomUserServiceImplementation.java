package com.teju.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.teju.model.User;
import com.teju.repository.UserRepository;

@Service
public class CustomUserServiceImplementation implements UserDetailsService{

	@Autowired
	private UserRepository ur;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		
		
		User user=ur.findbyEmail(username);
		if(user==null)
		{
			throw new UsernameNotFoundException("user not found with this email "+username);
		}
		List<GrantedAuthority> authorities=new ArrayList<>();
					
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
	}
	
	
}
