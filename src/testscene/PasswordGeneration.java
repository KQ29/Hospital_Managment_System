package testscene;

import java.security.SecureRandom;

public class PasswordGeneration {
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*";
    private static final String DICTIONARY = LOWERCASE + UPPERCASE + NUMBERS + SPECIAL_CHARS;
    private static final SecureRandom random = new SecureRandom();

    public static String generatePassword(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive");
        }

        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(DICTIONARY.length());
            password.append(DICTIONARY.charAt(index));
        }

        return password.toString();
    }
}
 