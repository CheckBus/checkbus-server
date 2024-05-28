package dgsw.bus.checkbus.user.adapter.in.dto.token;

import lombok.Data;

@Data
public class TokenResponseDto {
    private String accessToken;
    private String refreshToken;
}
