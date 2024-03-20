package com.teju.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.teju.model.TaskDTO;

@FeignClient(name="Submission-service",url="http://localhost:8082")

public interface Taskservice {

	@GetMapping("/api/taks/task/{id}")
	public TaskDTO getTask(@PathVariable Long id,@RequestHeader("Authorization") String jwt) throws Exception;

	
	@PutMapping("/api/taks/complete/{id}")
	public TaskDTO completetask(@PathVariable Long id) throws Exception;
	
}
