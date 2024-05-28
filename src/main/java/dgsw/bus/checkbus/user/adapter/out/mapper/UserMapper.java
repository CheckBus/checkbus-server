package dgsw.bus.checkbus.user.adapter.out.mapper;

import dgsw.bus.checkbus.global.annotation.Mapper;
import dgsw.bus.checkbus.user.adapter.out.entity.UserEntity;
import dgsw.bus.checkbus.user.domain.User;

@Mapper
public class UserMapper {
    public User toUser(UserEntity entity) {
        return new User(
            entity.getId(),
            entity.getName(),
            entity.getEmail(),
            entity.getProfileImage(),
            entity.getStdInfo(),
            entity.getRoles()
        );
    }

    public UserEntity toEntity(User user) {
        return new UserEntity(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getProfileImage(),
            user.getStdInfo(),
            user.getRoles()
        );
    }
}
