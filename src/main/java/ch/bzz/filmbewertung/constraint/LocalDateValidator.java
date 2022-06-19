package ch.bzz.filmbewertung.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * LocalDateValidator to validate the LocalDate with the right pattern
 * @author Erion Malaj
 */
public class LocalDateValidator implements ConstraintValidator<LocalDate, String> {

    /**
     * initialize Method
     * @param constraintAnnotation LocalDate passed
     */
    @Override
    public void initialize(LocalDate constraintAnnotation) {
    }

    /**
     * sees if the given string is a valid LocalDate
     * @param localDate given LocalDate as String
     * @param constraintValidatorContext given ConstraintValidatorContext
     * @return if String can be parsed or not
     */
    @Override
    public boolean isValid(String localDate, ConstraintValidatorContext constraintValidatorContext) {
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            simpleDateFormat.parse(localDate);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
