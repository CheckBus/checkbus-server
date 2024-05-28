package dgsw.bus.checkbus.user.application.port.in;

import dgsw.bus.checkbus.user.adapter.in.dto.token.DAuthApiRequestDto;
import dgsw.bus.checkbus.user.adapter.in.dto.token.TokenResponseDto;

public interface AuthUseCase {
    public TokenResponseDto getToken(DAuthApiRequestDto dAuthApiRequestDto);
}
