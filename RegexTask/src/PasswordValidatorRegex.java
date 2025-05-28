import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

// --- PasswordValidator2 ---
class PasswordValidatorRegex {
    private final int minLength;
    
    private static final Pattern DIGIT_PATTERN = Pattern.compile(".*\\d.*");
    private static final Pattern UPPERCASE_PATTERN = Pattern.compile(".*[A-Z].*");
    private static final Pattern LOWERCASE_PATTERN = Pattern.compile(".*[a-z].*");
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile(".*[^a-zA-Z0-9].*");

    public PasswordValidatorRegex(int minLength) {
        this.minLength = minLength;
    }

    void validate(String password) throws PasswordValidationException {
        List<Exception> errors = new ArrayList<>();
        
        if (password == null) {
            errors.add(new NullPasswordException());
            throw new PasswordValidationException(errors);
        }

        if (!Pattern.compile(".{" + minLength + ",}").matcher(password).matches()) {
            errors.add(new TooShortPasswordException(minLength));
        }
        
        if (!DIGIT_PATTERN.matcher(password).matches()) {
            errors.add(new NoDigitException());
        }
        
        if (!UPPERCASE_PATTERN.matcher(password).matches()) {
            errors.add(new NoUppercaseLetterException());
        }
        
        if (!LOWERCASE_PATTERN.matcher(password).matches()) {
            errors.add(new NoLowercaseLetterException());
        }
        
        if (!SPECIAL_CHAR_PATTERN.matcher(password).matches()) {
            errors.add(new NoSpecialCharacterException());
        }
        
        if (!errors.isEmpty()) {
            throw new PasswordValidationException(errors);
        }
    }
}
