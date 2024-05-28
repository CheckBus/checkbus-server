package dgsw.bus.checkbus.user.domain;


import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private String email;

    private String profileImage;
    private StdInfo stdInfo;
    private Roles roles;
}
