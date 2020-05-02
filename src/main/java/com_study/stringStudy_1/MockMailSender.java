package com_study.stringStudy_1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.mail.MailException;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;

public class MockMailSender implements MailSender{

	private List<String> requests = new ArrayList<String>();
	
	public List<String> getRequests(){
		return requests;
	}
	
	public void send(SimpleMailMessage mailMessage) throws MailException {
		requests.add(mailMessage.getTo()[0]);
	}


	public void send(SimpleMailMessage[] mailMessage) throws MailException {
		
	}
  
	
}
