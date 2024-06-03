package dgsw.bus.checkbus.bus.adapter.out.entity;

import dgsw.bus.checkbus.user.domain.Roles;
import dgsw.bus.checkbus.user.domain.StdInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bus")
public class BusEntity {
    @Id
    @NotNull
    @Column(name = "bus_code")
    private String busCode;
    private String hashCode;
}