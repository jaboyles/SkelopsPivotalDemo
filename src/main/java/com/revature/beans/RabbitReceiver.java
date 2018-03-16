package com.revature.beans;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.service.TRexService;

//@RabbitListener(queues = "trex")
@Component
public class RabbitReceiver {
	
	private static Logger log = LoggerFactory.getLogger(RabbitReceiver.class);
	
	@Autowired
	TRexService rexService; 
	
	//@RabbitHandler
	@RabbitListener(queues = "trex")
	public void receive(String jsonTRex) throws JsonParseException, JsonMappingException, IOException {
		log.info(jsonTRex);
		ObjectMapper mapper = new ObjectMapper();
		TRex t = mapper.readValue(jsonTRex, TRex.class);
		rexService.addTRex(t);
	}
	
}
