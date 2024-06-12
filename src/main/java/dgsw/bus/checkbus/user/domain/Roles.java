package dgsw.bus.checkbus.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Roles {
    USER("ROLE_USER", "일반 사용자"),
    STUDENT("ROLE_STUDENT", "학생"),
    TEACHER("ROLE_TEACHER", "교사");

    private final String key;
    private final String title;
}
