package dgsw.bus.checkbus.user.adapter.in.dto.token;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DAuthTokenResponseDto {

    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private String expiresIn;
}
