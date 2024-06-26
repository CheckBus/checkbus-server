package dgsw.bus.checkbus.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
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
                .description("dauth 로그인 URL : https://dauth.b1nd.com/login?client_id=c3ec0a0c666e4132b24923d013f547dae23fde8d232746dbb0330c44f236c4ba&redirect_uri=http://crafly.com/v1/auth/code")
                .contact(new Contact()
                        .name("dogbokchif")
                        .url("https://github.io/dogbokchif")
                        .email("cksgur0612@dgsw.hs.kr"))
                ;

        SecurityScheme auth = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.COOKIE).name("accessToken");
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("Auth");

        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .addSecurityItem(securityRequirement)
                .components(new Components().addSecuritySchemes("Auth", auth))
                .info(info);
    }
}
