package network.doctors.SanagaHealthNetwork.utility;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AgeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface ValidAge {
    String message() default "You must be {value} years and older to create an account";
    Class<?>[] groups() default {};
    Class<?  extends Payload>[] payload() default {};
    int value();
}
