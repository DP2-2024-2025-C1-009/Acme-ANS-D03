
package acme.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = PromotionCodeValidator.class)
@Target({
	ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPromotionCode {

	String message() default "";
	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
