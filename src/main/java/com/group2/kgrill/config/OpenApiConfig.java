package com.group2.kgrill.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "KGRILL REST API", version = "1.0", description = "API documentation for KGrill food delivery application",
        contact = @Contact(name = "Dang Dinh Tai (Group leader)", email = "styematic@gmail.com", url = "https://github.com/Taikt08s")),
        security = {@SecurityRequirement(name = "basicAuth"), @SecurityRequirement(name = "bearerToken")},
        servers = {
                @Server(
                        description = "Local environment",
                        url = "http://localhost:8080/api/v1/swagger-ui/index.html#"
                ),
                @Server(
                        description = "Deployment environment",
                        url = "https://kgrill-backend.onrender.com/api/v1/swagger-ui/index.html#"
                )
        }
)
@SecuritySchemes({
        @SecurityScheme(name = "basicAuth", type = SecuritySchemeType.HTTP, scheme = "basic"),
        @SecurityScheme(name = "bearerToken", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT", in = SecuritySchemeIn.HEADER)
})
public class OpenApiConfig {
}
