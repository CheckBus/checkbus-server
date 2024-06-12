package dgsw.bus.checkbus.global.handler;

import dgsw.bus.checkbus.global.exception.BackendException;
import dgsw.bus.checkbus.global.exception.ExceptionCode;
import dgsw.bus.checkbus.global.exception.ExceptionResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BackendException.class)
    protected ResponseEntity handleCustomException(BackendException e){
        return ExceptionResponseEntity.responseEntity(e.getExceptionCode());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity handleException(Exception e){
        log.error(e.toString());
        return ResponseEntity
            .status(500)
            .body(ExceptionResponseEntity.builder()
                .status(ExceptionCode.INTERNAL_SERVER_ERROR.getHttpStatus().value())
                .code(ExceptionCode.INTERNAL_SERVER_ERROR.name())
                .message(e.getMessage())
                .build());
    }
}
