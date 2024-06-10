package dgsw.bus.checkbus.user.adapter.out;

import dgsw.bus.checkbus.global.annotation.PersistenceAdapter;
import dgsw.bus.checkbus.global.exception.BackendException;
import dgsw.bus.checkbus.global.exception.ExceptionCode;
import dgsw.bus.checkbus.global.interceptor.jwt.JwtUtil;
import dgsw.bus.checkbus.user.adapter.in.dto.token.*;
import dgsw.bus.checkbus.user.adapter.out.entity.UserEntity;
import dgsw.bus.checkbus.user.adapter.out.mapper.UserMapper;
import dgsw.bus.checkbus.user.adapter.out.repository.UserRepository;
import dgsw.bus.checkbus.user.application.port.out.ManipulateUserPort;
import dgsw.bus.checkbus.user.application.port.out.ReadUserPort;
import dgsw.bus.checkbus.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserPersistenceAdapter implements ManipulateUserPort, ReadUserPort {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void registerUser() {
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        UserEntity entity = userRepository.findByEmail(user.getEmail());
        if (entity != null) userRepository.save(entity);
        else userRepository.save(userMapper.toEntity(user));
    }

    @Value("${dauth.client-url}")
    private String dauthUrl;

    @Value("${dauth.open-dodam-url}")
    private String dodamOpenApiUrl;
    @Override
    public TokenResponseDto getToken(DAuthApiRequestDto dAuthApiRequestDto) {
        HttpHeaders headers = new HttpHeaders();
        Map<String, String> data = new HashMap<String, String>();
        data.put("code", dAuthApiRequestDto.getCode());
        data.put("client_id", dAuthApiRequestDto.getClientId());
        data.put("client_secret", dAuthApiRequestDto.getClientSecret());

        HttpEntity<Map<String, String>> request = new HttpEntity<Map<String, String>>(data, headers);

        try {
            DAuthTokenResponseDto authTokenResponseDto = restTemplate.postForEntity(dauthUrl, request, DAuthTokenResponseDto.class).getBody();

            TokenResponseDto tokenResponseDto = getUserInfo(authTokenResponseDto);
            return tokenResponseDto;
        } catch (HttpClientErrorException e) {
            throw BackendException.of(ExceptionCode.WRONG_USER);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        return userMapper.toUser(userEntity);
    }

    private TokenResponseDto getUserInfo(DAuthTokenResponseDto dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer " + dto.getAccessToken());

        DAuthUserInfoResponseDto infoResponseDto = restTemplate.exchange(dodamOpenApiUrl, HttpMethod.GET, new HttpEntity<>(headers), DAuthUserInfoResponseDto.class).getBody();

        Optional.ofNullable(infoResponseDto.getData().getProfileImage()).ifPresent(
                s -> { if (s.contains(".null")) infoResponseDto.getData().setProfileImgNull(); });

        updateUser(infoResponseDto.getData().toEntity());

        return createUserToken(infoResponseDto.getData());
    }

    private TokenResponseDto createUserToken(DAuthUserInfoDataResponseDto DAuthUserInfoDataResponseDto) {
        return TokenResponseDto.builder()
                .accessToken(jwtUtil.generateAccessToken(DAuthUserInfoDataResponseDto.getEmail()))
                .refreshToken(jwtUtil.generateRefreshToken(DAuthUserInfoDataResponseDto.getEmail()))
                .build();
    }
}
