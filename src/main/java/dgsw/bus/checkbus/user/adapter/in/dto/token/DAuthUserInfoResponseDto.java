package dgsw.bus.checkbus.user.adapter.in.dto.token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DAuthUserInfoResponseDto {

    private String message;
    private DAuthUserInfoDataResponseDto data;
}
