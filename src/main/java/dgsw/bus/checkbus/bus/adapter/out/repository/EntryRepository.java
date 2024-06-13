
package dgsw.bus.checkbus.bus.adapter.out.repository;

import dgsw.bus.checkbus.bus.adapter.out.entity.BusEntity;
import dgsw.bus.checkbus.bus.adapter.out.entity.EntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryRepository extends JpaRepository<EntryEntity, Long> {
    public List<EntryEntity> findByBusCode(Long busCode);
    public void deleteByBusCode(Long busCode);
}
