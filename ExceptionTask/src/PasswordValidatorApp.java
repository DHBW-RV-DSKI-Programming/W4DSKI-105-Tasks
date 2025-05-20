public class PasswordValidatorApp {
    public static void main(String[] args) {
        PasswordValidator validator = new PasswordValidator(8);
        String[] passwords = {
                "Abc123!@#",     // valid
                "qwertzui",      // only lowercase letters
                "12345678",      // only digits
                "PASSWORD!",     // only uppercase letters and special character
                "Passw0rd",      // no special character
                "Pass!23",       // too short
                "Pa55word!",     // valid
                "P@ssword",      // no digit
                "Ab1!",          // much too short
                "Longenough1$",  // valid
                null             // null value
        };

        for (String pwd : passwords) {
            System.out.printf("\nTesting password: '%s'\n", pwd);
            try {
                validator.validate(pwd);
                System.out.println("→ Valid\n");
            } catch (PasswordValidationException e) {
                System.out.println("→ Invalid. Reasons:");
                e.getCauses().forEach(e1 -> System.out.printf("  - %s\n", e1.getMessage()));
            }
        }
    }
}