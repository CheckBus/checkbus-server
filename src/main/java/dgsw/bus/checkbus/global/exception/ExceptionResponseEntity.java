package dgsw.bus.checkbus.global.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class ExceptionResponseEntity {
    private int status;
    private String code;
    private String message;

    public static ResponseEntity<ExceptionResponseEntity> responseEntity(ExceptionCode e){
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(ExceptionResponseEntity.builder()
                        .status(e.getHttpStatus().value())
                        .code(e.name())
                        .message(e.getMessage())
                        .build());
    }
}
