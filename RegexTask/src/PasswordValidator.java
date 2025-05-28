import java.util.ArrayList;
import java.util.List;

// --- Exceptions ---
class NullPasswordException extends Exception {
    NullPasswordException() {
        super("The password is a non-existent value.");
    }
}

class TooShortPasswordException extends Exception {
    TooShortPasswordException(int minLength) {
        super("The password is too short. Minimum length: " + minLength);
    }
}

class NoSpecialCharacterException extends Exception {
    NoSpecialCharacterException() {
        super("The password must contain at least one special character.");
    }
}

class NoDigitException extends Exception {
    NoDigitException() {
        super("The password must contain at least one digit.");
    }
}

class NoUppercaseLetterException extends Exception {
    NoUppercaseLetterException() {
        super("The password must contain at least one uppercase letter.");
    }
}

class NoLowercaseLetterException extends Exception {
    NoLowercaseLetterException() {
        super("The password must contain at least one lowercase letter.");
    }
}

class PasswordValidationException extends Exception {
    private final List<Exception> causes;

    PasswordValidationException(List<Exception> causes) {
        super("The password does not meet the requirements.");
        this.causes = causes;
    }

    List<Exception> getCauses() {
        return causes;
    }
}

// --- PasswordValidator ---
class PasswordValidator {
    private final int minLength;

    PasswordValidator(int minLength) {
        this.minLength = minLength;
    }

    void validate(String password) throws PasswordValidationException {
        List<Exception> errors = new ArrayList<>();
        if (password == null) {
            errors.add(new NullPasswordException());
            throw new PasswordValidationException(errors);
        }
        if (password.length() < minLength) {
            errors.add(new TooShortPasswordException(minLength));
        }

        boolean hasDigit = false;
        boolean hasSpecial = false;
        boolean hasUpper = false;
        boolean hasLower = false;

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) hasDigit = true;
            else if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isLowerCase(c)) hasLower = true;
            else hasSpecial = true; // Character is not a letter or digit
        }

        if (!hasSpecial) errors.add(new NoSpecialCharacterException());
        if (!hasDigit) errors.add(new NoDigitException());
        if (!hasUpper) errors.add(new NoUppercaseLetterException());
        if (!hasLower) errors.add(new NoLowercaseLetterException());

        if (!errors.isEmpty()) {
            throw new PasswordValidationException(errors);
        }
    }
}