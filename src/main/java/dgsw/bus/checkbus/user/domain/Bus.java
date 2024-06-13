package dgsw.bus.checkbus.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Bus {
    private Long busCode;
    private String busName;
    private String hashCode;
    private int limit;
}
