package dgsw.bus.checkbus.user.adapter.in.dto.token;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenResponseDto {
    private String accessToken;
    private String refreshToken;
}
