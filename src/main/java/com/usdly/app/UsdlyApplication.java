package com.usdly.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.util.Arrays;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UsdlyApplication {

	private final static Logger LOG = LoggerFactory.getLogger(UsdlyApplication.class);

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(UsdlyApplication.class, args);

		LOG.debug("Let's inspect the beans provided by Spring Boot:");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			LOG.debug(beanName);
		}
	}
}
