package dgsw.bus.checkbus.bus.adapter.out.entity;

import dgsw.bus.checkbus.user.domain.Roles;
import dgsw.bus.checkbus.user.domain.StdInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bus")
@Builder
public class BusEntity {
    @Id
    @NotNull
    @Column(name = "bus_code")
    private Long busCode;
    private String busName;
    private String hashCode;
    private int limit;
}