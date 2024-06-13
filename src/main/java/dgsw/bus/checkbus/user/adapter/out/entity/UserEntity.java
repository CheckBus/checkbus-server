package dgsw.bus.checkbus.user.adapter.out.entity;

import dgsw.bus.checkbus.user.domain.Roles;
import dgsw.bus.checkbus.user.domain.StdInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @Column(name = "user_id")
    private String id;
    private String name;
    private String email;

    private String profileImage;
    @Embedded
    private StdInfo stdInfo;
    @Enumerated(EnumType.STRING)
    private Roles roles;
}
