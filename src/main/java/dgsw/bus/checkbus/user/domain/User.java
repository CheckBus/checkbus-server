package dgsw.bus.checkbus.user.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String email;

    private String profileImage;
    private StdInfo stdInfo;
    private Roles roles;
}
