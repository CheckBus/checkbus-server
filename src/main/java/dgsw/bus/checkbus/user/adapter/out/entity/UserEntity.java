package dgsw.bus.checkbus.user.adapter.out.entity;

import dgsw.bus.checkbus.user.domain.Roles;
import dgsw.bus.checkbus.user.domain.StdInfo;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String name;
    private String email;

    private String profileImage;
    @Embedded
    private StdInfo stdInfo;
    @Enumerated(EnumType.STRING)
    private Roles roles;
}
