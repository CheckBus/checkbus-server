package dgsw.bus.checkbus.user.adapter.out.repository;

import dgsw.bus.checkbus.user.adapter.out.entity.UserEntity;
import dgsw.bus.checkbus.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public UserEntity findByEmail(String email);
}
