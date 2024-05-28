package dgsw.bus.checkbus.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BackendException extends RuntimeException {
    private final ExceptionCode exceptionCode;

    public static BackendException of(ExceptionCode exceptionCode){
        return new BackendException(exceptionCode);
    }
}
