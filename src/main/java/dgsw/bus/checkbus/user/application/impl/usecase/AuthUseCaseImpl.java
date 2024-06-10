package dgsw.bus.checkbus.user.application.impl.usecase;

import dgsw.bus.checkbus.global.annotation.UseCase;
import dgsw.bus.checkbus.user.adapter.in.dto.token.DAuthApiRequestDto;
import dgsw.bus.checkbus.user.adapter.in.dto.token.DAuthTokenResponseDto;
import dgsw.bus.checkbus.user.adapter.in.dto.token.TokenResponseDto;
import dgsw.bus.checkbus.user.application.port.in.AuthUseCase;
import dgsw.bus.checkbus.user.application.port.out.ReadUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class AuthUseCaseImpl implements AuthUseCase {
    private final ReadUserPort readUserPort;
    @Override
    public TokenResponseDto getToken(DAuthApiRequestDto dAuthApiRequestDto) {
        return readUserPort.getToken(dAuthApiRequestDto);
    }
}
