package pl.jedrik94.demo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private Pattern pattern;

    @Override
    public void initialize(ValidEmail validEmail) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        Optional<String> optionalEmail = Optional.ofNullable(email);
        pattern = Pattern.compile(EMAIL_PATTERN);

        Matcher matcher = optionalEmail.map(s -> pattern.matcher(s)).orElseGet(() -> pattern.matcher(""));
        return matcher.matches();
    }
}
