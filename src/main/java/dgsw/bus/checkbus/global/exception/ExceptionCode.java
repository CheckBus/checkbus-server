package dgsw.bus.checkbus.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {
    NOT_FOUND(HttpStatus.NOT_FOUND, "찾을 수 없습니다"),
    WRONG_QR(HttpStatus.BAD_REQUEST, "QR을 생성할 수 없음"),
    WRONG_BUS(HttpStatus.BAD_REQUEST, "버스 정보를 가져올 수 없음"),
    WRONG_FILE(HttpStatus.BAD_REQUEST, "잘못된 파일 확장자"),
    S3_WRONG_FILE(HttpStatus.BAD_REQUEST, "파일을 찾을 수 없음"),
    WRONG_USER(HttpStatus.FORBIDDEN, "잘못된 사용자"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류"),
    INVALID_TOKEN(HttpStatus.FORBIDDEN, "유효하지 않은 토큰"),
    TOKEN_NOT_PROVIDED(HttpStatus.UNAUTHORIZED, "토큰이 입력되지 않았습니다"),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "만료된 토큰"),
    PARAMETER_IS_BAD(HttpStatus.BAD_REQUEST, "파라미터가 잘못됐습니다");

    private final HttpStatus httpStatus;
    private final String message;
}
