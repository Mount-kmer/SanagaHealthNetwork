package network.doctors.SanagaHealthNetwork.utility;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)


public @interface UniqueEmailAddress {

    String message() default "Email already exists.";
    Class<?>[] groups() default {};
    Class<?  extends Payload>[] payload() default {};

}
