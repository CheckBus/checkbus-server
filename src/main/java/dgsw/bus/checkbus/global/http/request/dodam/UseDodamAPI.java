package dgsw.bus.checkbus.global.http.request.dodam;

import dgsw.bus.checkbus.global.annotation.Util;
import dgsw.bus.checkbus.global.exception.BackendException;
import dgsw.bus.checkbus.global.exception.ExceptionCode;
import dgsw.bus.checkbus.global.http.request.RequestRestTemplate;
import dgsw.bus.checkbus.global.http.request.dto.DodamMemberRequestDto;
import dgsw.bus.checkbus.user.adapter.in.dto.token.DAuthTokenResponseDto;
import dgsw.bus.checkbus.user.adapter.in.dto.token.TokenResponseDto;
import dgsw.bus.checkbus.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@Util
@RequiredArgsConstructor
public class UseDodamAPI {
    private final RequestRestTemplate restTemplate;

    @Value("${dauth.open-dodam-url}")
    private String dodamOpenApiUrl;

    public User getDodamUser(String jwt) {
        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization", jwt);

        Map<String, String> data = new HashMap<String, String>();

        HttpEntity<Map<String, String>> request = new HttpEntity<Map<String, String>>(data, headers);

        try {
            return restTemplate.getForObject(dodamOpenApiUrl + "/member/my", DodamMemberRequestDto.class, request).getData();
        } catch (HttpClientErrorException e) {
            throw BackendException.of(ExceptionCode.WRONG_USER);
        }
    }
}
