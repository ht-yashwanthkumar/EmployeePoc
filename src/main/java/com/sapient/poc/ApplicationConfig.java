package com.sapient.poc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The {@code ApplicationConfig} class serves as configuring application
 * related.
 */
@Configuration
@EnableSwagger2
public class ApplicationConfig {

	/**
	 * method to initialize Docket to provide swagger documentation
	 * 
	 * @return docket instance
	 * 
	 */

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2);
	}
}
