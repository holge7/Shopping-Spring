package holge.shopping.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
	
	@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("REST API for manage Users")
                        .version("0.0.1")
                        .description("A User Service Microservice is a software component that is responsible for managing and manipulating product-related data in a microservice architecture. It is typically a RESTful web service that exposes a set of endpoints for creating, reading, updating, and deleting users.")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
	
}
