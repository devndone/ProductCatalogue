package org.dev.ecommerce.productcatalogue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("org.dev.ecommerce.productcatalogue.controller"))
				// .paths(PathSelectors.ant("/ProductCatalogue/*"))
				.build().apiInfo(apiInfo()).pathMapping("/");
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Product Catalogue Service")
				.description(
						"Product Catalogue REST service made with Spring Boot, Spring Data REST, JPA, Swagger, and Java 8 using In-Memory H2 DB")
				.contact(new Contact("Devendra Tiwari", "https://about.me/devendratiwari", "devndone@gmail.com"))
				.version("1.0").build();
	}

}
