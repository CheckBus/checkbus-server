package dgsw.bus.checkbus.bus.adapter.out.repository;

import dgsw.bus.checkbus.bus.adapter.out.entity.BusEntity;
import dgsw.bus.checkbus.user.adapter.out.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<BusEntity, Long> {
    public BusEntity findByBusCode(Long busCode);
    public void deleteByBusCode(Long busCode);
}
