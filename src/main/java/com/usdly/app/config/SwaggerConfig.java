package com.usdly.app.config;

import java.time.temporal.Temporal;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Lists;
import com.usdly.app.secuirty.CurrentUser;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * http://localhost:8080/builder/swagger-ui.html
 * 
 * @author satwa
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private final List<ResponseMessage> globalResponses = Arrays.asList(
			new ResponseMessageBuilder().code(200).message("Success").build(),
			new ResponseMessageBuilder().code(400).message("Bad request").build(),
			new ResponseMessageBuilder().code(401).message("Unauthorized").build(),
			new ResponseMessageBuilder().code(403).message("Forbidden").build(),
			new ResponseMessageBuilder().code(404).message("Not found").build(),
			new ResponseMessageBuilder().code(500).message("Internal server error").build());

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.usdly.app.web")).paths(PathSelectors.any()).build()
				.ignoredParameterTypes(CurrentUser.class).securityContexts(Lists.newArrayList(securityContext()))
				.securitySchemes(Lists.newArrayList(apiKey())).useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, globalResponses)
				.globalResponseMessage(RequestMethod.POST, globalResponses)
				.globalResponseMessage(RequestMethod.DELETE, globalResponses)
				.globalResponseMessage(RequestMethod.PUT, globalResponses)
				.directModelSubstitute(Temporal.class, String.class);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Usdly").description("Usdly api").version("1.0").build();
	}

	public static final String AUTHORIZATION_HEADER = "Authorization";
	public static final String DEFAULT_INCLUDE_PATTERN = "/controller/.*";

	private ApiKey apiKey() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Lists.newArrayList(new SecurityReference("JWT", authorizationScopes));
	}
}
