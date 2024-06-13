package dgsw.bus.checkbus.bus.adapter.in.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class DodamBusDto {
    private Long id;
    private String busName;
    private String description;
    private int peopleLimit;
    private int applyCount;
    private String leaveTime;
    private String timeRequired;
    private List<DodamMemberDto> members;
}
