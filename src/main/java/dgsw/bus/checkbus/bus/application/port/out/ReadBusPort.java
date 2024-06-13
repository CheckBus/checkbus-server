package dgsw.bus.checkbus.bus.application.port.out;

import dgsw.bus.checkbus.bus.adapter.out.entity.BusEntity;
import dgsw.bus.checkbus.user.domain.User;

public interface ReadBusPort {
    public BusEntity getBus(Long busCode);
}
