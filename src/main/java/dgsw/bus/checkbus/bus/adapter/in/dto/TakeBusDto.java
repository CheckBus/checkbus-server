package dgsw.bus.checkbus.bus.adapter.in.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TakeBusDto {
    private Long busCode;
    private String hashCode;
}
