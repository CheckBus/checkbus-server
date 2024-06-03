package dgsw.bus.checkbus.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Bus {
    private String busCode;
    private String hashCode;
}
