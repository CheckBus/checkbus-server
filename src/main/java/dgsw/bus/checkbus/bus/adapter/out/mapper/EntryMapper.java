package dgsw.bus.checkbus.bus.adapter.out.mapper;

import dgsw.bus.checkbus.bus.adapter.out.entity.BusEntity;
import dgsw.bus.checkbus.bus.adapter.out.entity.EntryEntity;
import dgsw.bus.checkbus.global.annotation.Mapper;
import dgsw.bus.checkbus.user.domain.Bus;
import dgsw.bus.checkbus.user.domain.Entry;

@Mapper
public class EntryMapper {
    public Entry toEntry(EntryEntity entity) {
        return new Entry(
            entity.getIdx(),
            entity.getBusCode(),
            entity.getUserId(),
            entity.getIsSubscription(),
            entity.getIsTake()
        );
    }

    public EntryEntity toEntity(Entry entry) {
        return new EntryEntity(
            entry.getIdx(),
            entry.getBusCode(),
            entry.getUserId(),
            entry.getIsSubscription(),
            entry.getIsTake()
        );
    }
}
