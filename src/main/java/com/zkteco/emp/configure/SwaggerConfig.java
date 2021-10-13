package com.zkteco.emp.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.zkteco.emp"))
				.paths(regex("/api/v1/employee.*"))
				.build()
				.apiInfo(metaInfo());	
	}
	private ApiInfo metaInfo() {
		ApiInfo apiInfo = new ApiInfo("Employee-Project",
										"Spring-Boot-gradle",
										"5.0",
										"terms and Condition",
										new Contact("Upendra", "https://www.zkteco.in", "upendra@zkteco.in"),
										"TomCat License Version 2.0",
										"https://www.tomcat.org/license.html");
		return apiInfo;
	}

}
