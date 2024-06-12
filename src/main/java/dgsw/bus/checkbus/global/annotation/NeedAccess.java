package dgsw.bus.checkbus.global.annotation;

import dgsw.bus.checkbus.user.domain.Roles;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface NeedAccess {
    @AliasFor(annotation = RequestMapping.class, attribute ="access")
    Roles access() default Roles.USER;
}
