package dgsw.bus.checkbus.bus.application.port.in;

import dgsw.bus.checkbus.bus.adapter.in.dto.TakeBusDto;
import dgsw.bus.checkbus.user.domain.User;

public interface BusUseCase {
    public void reloadBus();
    public byte[] getBusQR(Long busCode);
    public boolean checkBusQR(TakeBusDto takeBusDto, User user);
    public void closeBus(Long busCode);
}
