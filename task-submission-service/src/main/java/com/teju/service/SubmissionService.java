package com.teju.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teju.model.Submission;
import com.teju.model.TaskDTO;
import com.teju.repository.SubmissionRepository;

@Service
public class SubmissionService {

	@Autowired
	SubmissionRepository sr;
	
	@Autowired
	Taskservice ts;
	
	
	
	//createing a submission
	public Submission submittask(Long Taskid,String ghl,Long Userid,String jwt) throws Exception
	{
		
		TaskDTO t=ts.getTask(Taskid, jwt);
		if(t!=null)
		{
		Submission s=new Submission();
		s.setTaskid(Taskid);
		s.setUserId(Userid);
		s.setStatus("pending");
		s.setGithublink(ghl);
		s.setSubmissiontime(LocalDateTime.now());
		sr.save(s);
		return s;
		}
		else
			throw new Exception("task with this id is not present");
	}
	
	//getsubmission by id
	public Submission submissionbyid(Long sid)
	{
		Submission s=sr.findById(sid).orElse(null);
		
		return s;
	}
	
	//getallsubmissions
	public List<Submission> getallsubmissions()
	{
		List<Submission> s=sr.findAll();
		return s;
	}
	
	//list of submissions for a tasks
	public List<Submission> getallsubmissionsoftask(Long id)
	{
		
		List<Submission> s=sr.findbyTaskid(id);
		return s;
	}
	
	//acceptordeclinesubmission
	public Submission acceptorDecline(Long id,String status) throws Exception
	{
		Submission s=submissionbyid(id);
		if(s!=null)
		{
			s.setStatus(status);
			if(status=="ACCEPTED")
				ts.completetask(s.getTaskid());
			
			return sr.save(s);
		}
		else
			throw new Exception("Submission with this id is npot present");
	}
	
}
