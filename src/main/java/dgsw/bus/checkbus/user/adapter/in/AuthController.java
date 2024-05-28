package dgsw.bus.checkbus.user.adapter.in;

import dgsw.bus.checkbus.global.annotation.NeedAccess;
import dgsw.bus.checkbus.global.annotation.WebAdapter;
import dgsw.bus.checkbus.global.model.DataResponse;
import dgsw.bus.checkbus.user.adapter.in.dto.token.AccessTokenDto;
import dgsw.bus.checkbus.user.adapter.in.dto.token.DAuthApiRequestDto;
import dgsw.bus.checkbus.user.adapter.in.dto.token.DAuthClientRequestDto;
import dgsw.bus.checkbus.user.adapter.in.dto.token.TokenResponseDto;
import dgsw.bus.checkbus.user.application.port.in.AuthUseCase;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter(path = "Auth-Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    @Value("${dauth.client-id}")
    private String clientId;
    @Value("${dauth.client-secret}")
    private String clientSecret;

    private final AuthUseCase authUseCase;

    @Operation(summary = "Token 받기")
    @PostMapping("/code")
    public ResponseEntity<DataResponse<TokenResponseDto>> resCode(@RequestBody DAuthClientRequestDto dAuthClientRequestDto){
        TokenResponseDto token = authUseCase.getToken(new DAuthApiRequestDto(dAuthClientRequestDto.getCode(), clientId, clientSecret));
        return DataResponse.ok("인증 성공", token);
    }

    @Operation(summary = "AccessToken 재발급")
    @GetMapping("/refreshToken")
    @NeedAccess
    public ResponseEntity<DataResponse<AccessTokenDto>> getAccessToken(@RequestAttribute("accessToken") AccessTokenDto accessTokenDto) {
        return DataResponse.ok("토큰 생성 성공", accessTokenDto);
    }

}

