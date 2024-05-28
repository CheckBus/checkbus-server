package dgsw.bus.checkbus.user.adapter.out;

import dgsw.bus.checkbus.global.annotation.PersistenceAdapter;
import dgsw.bus.checkbus.user.adapter.out.entity.UserEntity;
import dgsw.bus.checkbus.user.adapter.out.mapper.UserMapper;
import dgsw.bus.checkbus.user.adapter.out.repository.UserRepository;
import dgsw.bus.checkbus.user.application.port.out.ManipulateUserPort;
import dgsw.bus.checkbus.user.application.port.out.ReadUserPort;
import dgsw.bus.checkbus.user.domain.User;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserPersistenceAdapter implements ManipulateUserPort, ReadUserPort {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void registerUser() {

    }

    @Override
    public void updateUser() {

    }

    @Override
    public User getUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        return userMapper.toUser(userEntity);
    }
}
