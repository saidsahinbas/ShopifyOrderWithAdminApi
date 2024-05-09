package com.Saiddev.ShopifyOrderTracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ShopifyOrderTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopifyOrderTrackingApplication.class, args);
	}

}
