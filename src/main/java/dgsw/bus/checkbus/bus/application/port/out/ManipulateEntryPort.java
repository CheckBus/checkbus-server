package dgsw.bus.checkbus.bus.application.port.out;

import dgsw.bus.checkbus.bus.adapter.in.dto.TakeBusDto;
import dgsw.bus.checkbus.user.domain.User;

public interface ManipulateEntryPort {
    public void checkEntry(TakeBusDto takeBusDto, User user);
}
