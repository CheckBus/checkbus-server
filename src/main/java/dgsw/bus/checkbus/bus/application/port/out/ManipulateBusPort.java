package dgsw.bus.checkbus.bus.application.port.out;

import dgsw.bus.checkbus.bus.adapter.in.dto.DodamBusListRequestDto;

public interface ManipulateBusPort {

    public void registerBus(DodamBusListRequestDto dodamBusListRequestDto);
    public void removeBus(Long busCode);
}
