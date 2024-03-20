package com.teju.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.teju.model.UserDto;

@FeignClient(name="user-servivce",url="http://localhost:8081")
public interface UserService {

	@GetMapping("/api/user/profile")
	public UserDto getUserProfile(@RequestHeader("Authorization") String jwt);
	
}
