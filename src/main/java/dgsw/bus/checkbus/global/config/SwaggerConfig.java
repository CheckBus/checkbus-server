package dgsw.bus.checkbus.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Check Bus API")
                .version("v1")
                .description("대소고 버스 관리 서비스")
                .contact(new Contact()
                        .name("dogbokchif")
                        .url("https://github.io/dogbokchif")
                        .email("cksgur0612@dgsw.hs.kr"))
                ;

        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .components(new Components())
                .info(info);
    }
}
