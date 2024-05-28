package dgsw.bus.checkbus.user.application.port.out;

import dgsw.bus.checkbus.user.domain.User;

public interface ReadUserPort {
    public User getUserByEmail(String email);
}
