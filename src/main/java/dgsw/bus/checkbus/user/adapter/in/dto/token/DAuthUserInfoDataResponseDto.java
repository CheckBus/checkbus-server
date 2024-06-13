package dgsw.bus.checkbus.user.adapter.in.dto.token;

import dgsw.bus.checkbus.user.domain.Roles;
import dgsw.bus.checkbus.user.domain.StdInfo;
import dgsw.bus.checkbus.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DAuthUserInfoDataResponseDto {
    private String uniqueId;
    private int grade;
    private int room;
    private int number;
    private String name;
    private String profileImage;
    private String email;
    private String role;

    public User toEntity(){
        return User.builder()
                .id(uniqueId)
                .name(name)
                .email(email)
                .profileImage(profileImage)
                .stdInfo(new StdInfo(grade, room, number))
                .roles(Roles.valueOf(role))
                .build();
    }

    public void setProfileImgNull() {
        this.profileImage = null;
    }
}
