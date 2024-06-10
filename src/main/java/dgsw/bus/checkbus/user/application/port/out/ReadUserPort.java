package dgsw.bus.checkbus.user.application.port.out;

import dgsw.bus.checkbus.user.adapter.in.dto.token.DAuthApiRequestDto;
import dgsw.bus.checkbus.user.adapter.in.dto.token.TokenResponseDto;
import dgsw.bus.checkbus.user.domain.User;

public interface ReadUserPort {
    public TokenResponseDto getToken(DAuthApiRequestDto dAuthApiRequestDto);
    public User getUserByEmail(String email);
}
