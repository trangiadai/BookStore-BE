package com.tgd.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

	private static final String SECURITY_SCHEME_NAME = "BearerAuth";

	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("E-Commerce REST API").version("1.0.0")
						.description("API Documentation protected by Spring Security OAuth2 Resource Server & JWT"))
				// Applies JWT authorization globally to all API endpoints in Swagger UI
				.addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
				// Registers the Bearer token scheme in Swagger UI components
				.components(new Components().addSecuritySchemes(SECURITY_SCHEME_NAME,
						new SecurityScheme().name(SECURITY_SCHEME_NAME).type(SecurityScheme.Type.HTTP).scheme("bearer")
								.bearerFormat("JWT").description("Enter your JWT token obtained from /auth/login")));
	}
}