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
            entity.getBusName(),
            entity.getHashCode(),
            entity.getLimit()
        );
    }

    public BusEntity toEntity(Bus bus) {
        return new BusEntity(
            bus.getBusCode(),
            bus.getBusName(),
            bus.getHashCode(),
            bus.getLimit()
        );
    }
}
