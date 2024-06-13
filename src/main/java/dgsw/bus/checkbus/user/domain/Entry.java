package dgsw.bus.checkbus.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Entry {
    private Long idx;
    private Long busCode;
    private String userId;
    private Boolean isSubscription;
    private Boolean isTake;
}
