package com.revature;

import java.io.IOException;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.TRex;

@SpringBootApplication
@RabbitListener(queues = "foo")
@EnableScheduling
public class Application extends SpringBootServletInitializer {
	
	@Bean
	public Queue fooQueue() {
		return new Queue("trex");
	}
	
	@RabbitHandler
	public void process(@Payload String foo) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode j = mapper.readTree(foo);
		TRex rex = new TRex(j.get("name").asText(), j.get("featherColor").asText());
		System.err.println(rex.toString());
	}
	
	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(Application.class);
	}

}
