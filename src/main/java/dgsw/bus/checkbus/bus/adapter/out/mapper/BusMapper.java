package dgsw.bus.checkbus.bus.adapter.out.mapper;

import dgsw.bus.checkbus.bus.adapter.out.entity.BusEntity;
import dgsw.bus.checkbus.global.annotation.Mapper;
import dgsw.bus.checkbus.user.adapter.out.entity.UserEntity;
import dgsw.bus.checkbus.user.domain.Bus;
import dgsw.bus.checkbus.user.domain.User;

@Mapper
public class BusMapper {
    public Bus toBus(BusEntity entity) {
        return new Bus(
            entity.getBusCode(),
            entity.getHashCode()
        );
    }

    public BusEntity toEntity(Bus bus) {
        return new BusEntity(
            bus.getBusCode(),
            bus.getHashCode()
        );
    }
}
