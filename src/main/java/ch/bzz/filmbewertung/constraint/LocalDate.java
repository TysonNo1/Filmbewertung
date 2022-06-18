package ch.bzz.filmbewertung.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * LocalDate Constraint set on String fields that can be LocalDate
 * @author Erion Malaj
 */
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LocalDateValidator.class)
@Documented
public @interface LocalDate {

    String message() default "must be a valid LocalDate with pattern yyyy-MM-dd. found: ${validatedValue}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
