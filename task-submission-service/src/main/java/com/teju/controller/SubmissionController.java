package com.teju.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teju.model.Submission;
import com.teju.model.UserDto;
import com.teju.service.SubmissionService;
import com.teju.service.Taskservice;
import com.teju.service.UserService;

@RestController
@RequestMapping("/api/submission")
public class SubmissionController {
	
	@Autowired 
	Taskservice ts;
	
	@Autowired
	UserService us;
	
	@Autowired 
	SubmissionService ss;
	
	//submit a task
	@PostMapping("/submit")
	public ResponseEntity<Submission> submit(@RequestParam Long tid,@RequestParam String ghl,@RequestHeader("Authorization") String jwt) throws Exception 
	{
		UserDto user=us.getUserProfile(jwt);
		Submission s=ss.submittask(tid, ghl, user.getId(), jwt);
		return new ResponseEntity<>(s,HttpStatus.CREATED);
		
	}
	
	//get submission by id
	@GetMapping("/{id}")
	public ResponseEntity<Submission> getsubmissionbyid(@PathVariable Long id)
	{
		Submission s=ss.submissionbyid(id);
		return new ResponseEntity<>(s,HttpStatus.OK);
	}
	
	//get all submissions
	@GetMapping("/all")
	public ResponseEntity<List<Submission>> getsubmissions()
	{
		List<Submission> s=ss.getallsubmissions();
		return new ResponseEntity<>(s,HttpStatus.OK);
	}
	
	//get all submissions of a task
	@GetMapping("/all/{tid}")
	public ResponseEntity<List<Submission>> getsubmissionsoftask(@PathVariable Long tid)
	{
		List<Submission> s=ss.getallsubmissionsoftask(tid);
		return new ResponseEntity<>(s,HttpStatus.OK);
	}
	
	@PutMapping("/aord/{sid}/{status}")
	public ResponseEntity<Submission> acceptordd(@PathVariable Long sid,@PathVariable String status) throws Exception
	{
		Submission s=ss.acceptorDecline(sid, status);
		return new ResponseEntity<>(s,HttpStatus.OK);
	}
	
	
	
	
	

}
