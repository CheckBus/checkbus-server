package dgsw.bus.checkbus.global.annotation;

import dgsw.bus.checkbus.user.domain.Roles;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface NeedAccess {
    Roles[] access() default Roles.USER;
}
