package com.revature;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.revature.beans.RabbitReceiver;

@SpringBootApplication
@RabbitListener(queues = "trex")
@EnableEurekaClient
@EnableScheduling
public class Application extends SpringBootServletInitializer {
	
	@Bean
	public Queue fooQueue() {
		return new Queue("trex");
	}
	
	@Profile("receiver")
    @Bean
    public RabbitReceiver receiver() {
        return new RabbitReceiver();
    }
	
	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(Application.class);
	}

}
