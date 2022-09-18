package br.com.challenge.financeiro.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

	private static final String AUTHORIZATION_KEY = "bearer-key";

	@Value("${info.app.version}")
	private String version;

	@Value("${springdoc.info.description}")
	private String description;

	@Value("${springdoc.info.title}")
	private String title;

	@Bean
	public OpenAPI infoApi() {
		
		ExternalDocumentation externalDoc = new ExternalDocumentation();
		externalDoc.description("SpringShop Wiki Documentation")
		.url("https://springshop.wiki.github.org/docs");

		Info info = new Info();
		info.setTitle(title);

		SecurityRequirement securityItem = new SecurityRequirement();
		securityItem.addList(AUTHORIZATION_KEY);

		Components components = new Components();
		components.addSecuritySchemes(AUTHORIZATION_KEY, new SecurityScheme()
				.type(SecurityScheme.Type.HTTP)
				.scheme("bearer")
				.in(SecurityScheme.In.HEADER)
				.bearerFormat("JWT"));

		return new OpenAPI().components(components)
				.externalDocs(externalDoc)
				.addSecurityItem(securityItem)
				.info(info.description(description).version(version));
	}

}
