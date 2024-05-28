package dgsw.bus.checkbus.user.application.impl.usecase;

import dgsw.bus.checkbus.global.annotation.UseCase;
import dgsw.bus.checkbus.user.adapter.in.dto.token.DAuthApiRequestDto;
import dgsw.bus.checkbus.user.adapter.in.dto.token.TokenResponseDto;
import dgsw.bus.checkbus.user.application.port.in.AuthUseCase;

@UseCase
public class AuthUseCaseImpl implements AuthUseCase {
    @Override
    public TokenResponseDto getToken(DAuthApiRequestDto dAuthApiRequestDto) {
        return null;
    }
}
