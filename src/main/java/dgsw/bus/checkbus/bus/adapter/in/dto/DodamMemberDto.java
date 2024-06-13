package dgsw.bus.checkbus.bus.adapter.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class DodamMemberDto {
    private String memberId;
    private String name;
    private String email;
    private String phone;
}
