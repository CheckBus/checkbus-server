package dgsw.bus.checkbus.bus.adapter.in.dto;

import dgsw.bus.checkbus.user.adapter.in.dto.token.DAuthUserInfoDataResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class    DodamBusListRequestDto {
    private String message;
    private List<DodamBusDto> data;
}
