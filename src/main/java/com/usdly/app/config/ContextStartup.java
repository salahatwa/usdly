package com.usdly.app.config;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.ServletContextAware;

import com.usdly.app.domain.Options;
import com.usdly.app.services.OptionsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(2)
@Component
public class ContextStartup implements ApplicationRunner, ServletContextAware {
	@Autowired
	private OptionsService optionsService;

	@Autowired
	private ServletContext servletContext;

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		log.info("initialization ...");
		reloadOptions(true);
		log.info("OK, completed");
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public void reloadOptions(boolean startup) {
		List<Options> options = optionsService.findAll();

		log.info("find options ({})...", options.size());

		if (startup && CollectionUtils.isEmpty(options)) {
			try {
				log.info("init options...");
				Resource resource = new ClassPathResource("/scripts/schema.sql");
				optionsService.initSettings(resource);
				options = optionsService.findAll();
			} catch (Exception e) {
				log.error("------------------------------------------------------------");
				log.error("-          ERROR: The database is not initialized          -");
				log.error("------------------------------------------------------------");
				log.error(e.getMessage(), e);
				System.exit(1);
			}
		}

	}

}
