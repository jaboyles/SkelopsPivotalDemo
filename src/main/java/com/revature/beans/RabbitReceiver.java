package com.revature.beans;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.service.TRexService;

@RabbitListener(queues = "trex")
public class RabbitReceiver {
	
	@Autowired
	TRexService rexService;
	
	@RabbitHandler
	public void receive(String jsonTRex) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		TRex t = mapper.readValue(jsonTRex, TRex.class);
		rexService.addTRex(t);
	}
	
}
