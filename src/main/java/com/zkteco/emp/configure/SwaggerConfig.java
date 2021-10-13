package com.zkteco.emp.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.google.common.base.Predicates;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("com.zkteco.emp")))
				.paths(regex("/api/v1/employee.*"))
				.build()
				.apiInfo(metaInfo());	
	}
//	public void addResourcehandler(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resource/");
//		
//	}

	private ApiInfo metaInfo() {
		ApiInfo apiInfo = new ApiInfo("Employee",
										"Spring-Boot-Project",
										"5.0",
										"terms and Condition",
										new Contact("zkteco", "https://www.zkteco.in", "upendra@zkteco.in"),
										"TomCat License Version 2.0",
										"https://www.tomcat.org/license.html");
		return apiInfo;
	}

}
