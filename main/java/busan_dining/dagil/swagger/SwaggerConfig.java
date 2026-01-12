package busan_dining.dagil.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Busan Dining")
                .version("1.0")
                .description("This is the recommendation service which recommend restaurants in Busan.")
                .contact(new io.swagger.v3.oas.models.info.Contact().email("cheve1224@pusan.ac.kr"));

        // JWT 설정 추가
        String jwtScheme = "jwtAuth";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtScheme);
        Components components = new Components()
                .addSecuritySchemes(jwtScheme, new SecurityScheme()
                        .name("Authorization")
                        .type(SecurityScheme.Type.HTTP)
                        .in(SecurityScheme.In.HEADER)
                        .scheme("Bearer")
                        .bearerFormat("JWT"));

        // swagger 서버 & 보안
        return new OpenAPI()
                .addServersItem(new Server().url("http://localhost:8080"))
                .components(components)
                .info(info)
                .addSecurityItem(securityRequirement);
    }
}
