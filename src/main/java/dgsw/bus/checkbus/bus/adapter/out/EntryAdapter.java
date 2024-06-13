package dgsw.bus.checkbus.bus.adapter.out;

import dgsw.bus.checkbus.bus.adapter.in.dto.TakeBusDto;
import dgsw.bus.checkbus.bus.adapter.out.entity.BusEntity;
import dgsw.bus.checkbus.bus.adapter.out.entity.EntryEntity;
import dgsw.bus.checkbus.bus.adapter.out.repository.BusRepository;
import dgsw.bus.checkbus.bus.adapter.out.repository.EntryRepository;
import dgsw.bus.checkbus.bus.application.port.out.ManipulateEntryPort;
import dgsw.bus.checkbus.global.annotation.PersistenceAdapter;
import dgsw.bus.checkbus.user.domain.User;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class EntryAdapter implements ManipulateEntryPort {
    private final BusRepository busRepository;
    private final EntryRepository entryRepository;

    @Override
    public void checkEntry(TakeBusDto takeBusDto, User user) {
        BusEntity bus = busRepository.findByBusCode(takeBusDto.getBusCode());
        int ent = entryRepository.findByBusCode(bus.getBusCode()).size();

        if (bus.getLimit() > ent) {
            entryRepository.save(
                EntryEntity.builder()
                    .busCode(bus.getBusCode())
                    .userId(user.getId())
                    .isTake(true)
                    .build()
            );
        }
    }
}
