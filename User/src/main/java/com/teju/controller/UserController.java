package com.teju.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teju.model.User;
import com.teju.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	
	@Autowired
	UserService use;
	
	
	@GetMapping("/profile")
	public ResponseEntity<User> getprofile(@RequestHeader("Authorization") String jwt)
	{
		System.out.println("email");
	  User u=use.getuserProfile(jwt);
	  return new ResponseEntity<>(u,HttpStatus.OK);
	  
		
	}
}
