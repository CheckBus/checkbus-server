package dgsw.bus.checkbus.user.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String email;

    private String profileImage;
    private StdInfo stdInfo;
    private Roles roles;
}
