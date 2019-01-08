package pl.jedrik94.demo.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String firstStringField;
    private String secondStringField;
    private String message;

    @Override
    public void initialize(FieldMatch fieldMatch) {
        this.firstStringField = fieldMatch.firstString();
        this.secondStringField = fieldMatch.secondString();
        this.message = fieldMatch.message();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        final Boolean result;

        Optional<Object> firstOptionalObject = Optional.ofNullable(new BeanWrapperImpl(object).getPropertyValue(firstStringField));
        Optional<Object> secondOptionalObject = Optional.ofNullable(new BeanWrapperImpl(object).getPropertyValue(secondStringField));

        // May be buggy
        result = firstOptionalObject.flatMap(firstObject -> secondOptionalObject.map(firstObject::equals)).orElse(false);

        if (!result) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(firstStringField)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return result;
    }
}
