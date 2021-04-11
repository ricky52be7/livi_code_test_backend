package com.livi.codetest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityScheme;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public OpenAPI customOpenAPI(@Value("${springdoc.oAuthFlow.tokenUrl}") String tokenUrl) {
		return new OpenAPI().components(new Components().addSecuritySchemes("oauth2Scheme",
				new SecurityScheme().type(SecurityScheme.Type.OAUTH2)
						.flows(new OAuthFlows().password(new OAuthFlow().tokenUrl(tokenUrl)))));
	}

}
