package by.max.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI hskTrainerOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("HSKTrainer API")
                        .description("Документация бэкенда для приложения изучения китайских иероглифов HSKTrainer")
                        .version("1.0.0"));
    }
}
