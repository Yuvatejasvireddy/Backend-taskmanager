package com.teju.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teju.models.Task;
import com.teju.models.UserDto;
import com.teju.service.TaskService;
import com.teju.service.UserService;

@RestController
@RequestMapping("/api/tasks")
public class TaskControllers {
	
@Autowired
TaskService ts;

@Autowired
UserService us;
//create a task

@PostMapping("/createTask")
public ResponseEntity<Task> createTask(@RequestBody Task task,@RequestHeader("Authorization") String jwt) throws Exception
{
	UserDto user=us.getUserProfile(jwt);
	Task createdtask=ts.createtask(task, user.getRole());
	
	return new ResponseEntity<>(createdtask,HttpStatus.CREATED);
}

//get task by id

@GetMapping("/task/{id}")
public ResponseEntity<Task> getTask(@PathVariable Long id,@RequestHeader("Authorization") String jwt) throws Exception
{
	UserDto user=us.getUserProfile(jwt);
	Task task=ts.getbyid(id);
	
	return new ResponseEntity<>(task,HttpStatus.OK);
}

//get all tasks based on the status 
@GetMapping("/getalltasks")
public ResponseEntity<List<Task>> getalltasks(@RequestParam(required=false) String status,@RequestHeader("Authorization") String jwt)throws Exception
{
	UserDto user=us.getUserProfile(jwt);
	List<Task> all=ts.getallTask(status);
	return new ResponseEntity<>(all,HttpStatus.OK);
}

//assign a user to a task
@PutMapping("/{id}/user/{uid}")
public ResponseEntity<Task> assignuser(@PathVariable Long id,@PathVariable Long uid,@RequestHeader("Authorization") String jwt) throws Exception
{
	UserDto user=us.getUserProfile(jwt);
	Task t=ts.assignedtouser(id, uid);
	return new ResponseEntity<>(t,HttpStatus.OK);
}

//updatetask
@PutMapping("/{id}")
public ResponseEntity<Task> updatetask(@PathVariable Long id,@RequestBody Task t,@RequestHeader("Authorization") String jwt) throws Exception
{
	UserDto user=us.getUserProfile(jwt);
	
	Task updated=ts.updateTask(id, t,user.getId());
	return new ResponseEntity<>(updated,HttpStatus.OK);
	
}

//complete the task

@PutMapping("/complete/{id}")
public ResponseEntity<Task> completetask(@PathVariable Long id) throws Exception
{
	Task t=ts.getbyid(id);
	t.setStatus("done");
	return new ResponseEntity<>(t,HttpStatus.OK);
}

//Deleting the task

@DeleteMapping("/delete/{id}")
public void deletetask(@PathVariable Long id)
{
	ts.deletetask(id);
}


//getting all the tasks assigned to a user {can be filtered with a status }
@GetMapping("/user/{id}")
public ResponseEntity<List<Task>> gettaskofUser(@PathVariable Long id,@RequestParam(required=false) String status,@RequestHeader("Authorization") String jwt)
{
	UserDto user=us.getUserProfile(jwt);
	List<Task> t=ts.assignedusertask(id, status);
	return new ResponseEntity<>(t,HttpStatus.OK);
	
}







}
