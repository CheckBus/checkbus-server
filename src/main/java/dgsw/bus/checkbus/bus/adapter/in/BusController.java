package dgsw.bus.checkbus.bus.adapter.in;

import dgsw.bus.checkbus.bus.application.port.in.BusUseCase;
import dgsw.bus.checkbus.global.annotation.NeedAccess;
import dgsw.bus.checkbus.global.annotation.WebAdapter;
import dgsw.bus.checkbus.global.model.DataResponse;
import dgsw.bus.checkbus.user.adapter.in.dto.token.DAuthApiRequestDto;
import dgsw.bus.checkbus.user.adapter.in.dto.token.DAuthClientRequestDto;
import dgsw.bus.checkbus.user.adapter.in.dto.token.TokenResponseDto;
import dgsw.bus.checkbus.user.application.port.in.AuthUseCase;
import dgsw.bus.checkbus.user.domain.Roles;
import dgsw.bus.checkbus.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter(path = "/v1/bus")
@RequiredArgsConstructor
public class BusController {
    private final BusUseCase busUseCase;

    @NeedAccess(access = Roles.TEACHER)
    @Operation(summary = "도담 Bus 가져오기")
    @PostMapping("/reload")
    public ResponseEntity<DataResponse<String>> loadBus() {
        busUseCase.reloadBus();
        return DataResponse.ok("성공", "");
    }

    @NeedAccess(access = Roles.TEACHER)
    @Operation(summary = "Bus QR 가져오기")
    @GetMapping("/get-qr")
    public ResponseEntity<byte[]> getQR(@RequestParam String busCode){
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(busUseCase.getBusQR(busCode));
    }

    @NeedAccess(access = Roles.STUDENT)
    @Operation(summary = "Bus 탑승 요청하기")
    @PostMapping("/take-bus")
    public ResponseEntity<DataResponse<String>> takeBus(
            @RequestParam String busCode,
            @RequestParam String hashCode,
            @RequestAttribute User user
            ) {
        return DataResponse.ok("탑승 완료", "");
    }

    @NeedAccess(access = Roles.TEACHER)
    @Operation(summary = "Bus 닫기")
    @DeleteMapping("/close")
    public ResponseEntity<DataResponse<String>> closeBus(@RequestParam String busCode){
        busUseCase.closeBus(busCode);
        return DataResponse.ok("성공", busCode);
    }
}
