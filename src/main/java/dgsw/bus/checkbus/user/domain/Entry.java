package dgsw.bus.checkbus.user.domain;

import lombok.Data;

@Data
public class Entry {
    private Long idx;
    private Long busCode;
    private Long userId;
    private Boolean isSubscription;
    private Boolean isTake;
}
