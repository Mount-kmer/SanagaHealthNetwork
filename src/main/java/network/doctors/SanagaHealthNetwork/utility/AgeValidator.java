package network.doctors.SanagaHealthNetwork.utility;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class AgeValidator implements ConstraintValidator<ValidAge, Date> {

    private int minimumRegistrationAge;

    @Override
    public void initialize(ValidAge constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.minimumRegistrationAge = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Date dateOfBirth, ConstraintValidatorContext context) {
        if(dateOfBirth == null) {
            return true;
        }

        Date currentDate = new Date();
        long ageInMill = currentDate.getTime() - dateOfBirth.getTime();
        long ageInYears = ageInMill / (1000L * 60 * 60 * 24 * 365);

        if (ageInYears >= minimumRegistrationAge){
            return true;
        } else {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("You must be {value} years and older to create " +
                    "an account")
                    .addConstraintViolation();
            return false;
        }
    }
}
