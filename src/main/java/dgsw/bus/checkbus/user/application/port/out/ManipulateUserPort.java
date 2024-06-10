package dgsw.bus.checkbus.user.application.port.out;

import dgsw.bus.checkbus.user.domain.User;

public interface ManipulateUserPort {

    public void registerUser();
    public void updateUser(User user);
}
