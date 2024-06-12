package dgsw.bus.checkbus.global.http.request.dto;


import dgsw.bus.checkbus.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DodamMemberRequestDto {
    private String message;
    private User data;
}
