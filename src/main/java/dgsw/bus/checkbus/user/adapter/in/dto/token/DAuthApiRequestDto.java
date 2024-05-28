package dgsw.bus.checkbus.user.adapter.in.dto.token;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DAuthApiRequestDto {

    private String code;
    private String clientId;
    private String clientSecret;
}
