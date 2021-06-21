package br.com.zevolution.imdb.config.swagger;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket productApi() {
	    return new Docket(DocumentationType.SWAGGER_2)
				.groupName("V1.0.0")
	            .select()
	            .apis(RequestHandlerSelectors.basePackage("br.com.zevolution.imdb.api"))
	            .paths(PathSelectors.any())
	            .build()
	            .useDefaultResponseMessages(true)
	            .securitySchemes(Arrays.asList(this.apiKey()))
	            .apiInfo(this.getAPIInfo())
	            .securityContexts(Arrays.asList(this.securityContext()));
    }
	    
	private ApiInfo getAPIInfo() {
		return new ApiInfoBuilder()
	            .title("IMDb API")
	            .description("Public API's")
	            .version("1.0.0")
	            .license("MIT")
	            .licenseUrl("https://opensource.org/licenses/MIT")
	            .contact(new Contact("Jose Lucas", "https://github.com/zevolution", "contato@zevolution.com.br"))
	            .build();
	}
	
	private ApiKey apiKey() {
		return new ApiKey("Bearer", "Authorization", "header");
	}
	
    private SecurityContext securityContext() {
        return SecurityContext.builder()
        		.securityReferences(defaultAuth())
        		.forPaths(Predicates.not(PathSelectors.regex("/auth/.*")))
        		.build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("Bearer", authorizationScopes));
    }

}
