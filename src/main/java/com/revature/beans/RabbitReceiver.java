package com.revature.beans;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.service.TRexService;

@RabbitListener(queues = "trex")
public class RabbitReceiver {
	
	@Autowired
	TRexService rexService;
	
	@RabbitHandler
	public void receive(String in) {
		System.err.println(in);
	}
	
}
