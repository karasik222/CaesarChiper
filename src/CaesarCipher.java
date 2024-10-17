public class CaesarCipher {

    private static final String LATIN_ALPHABET_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LATIN_ALPHABET_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String RUSSIAN_ALPHABET_UPPER = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final String RUSSIAN_ALPHABET_LOWER = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (LATIN_ALPHABET_UPPER.indexOf(c) != -1) {
                encrypted.append(shiftChar(c, key, LATIN_ALPHABET_UPPER));
            } else if (LATIN_ALPHABET_LOWER.indexOf(c) != -1) {
                encrypted.append(shiftChar(c, key, LATIN_ALPHABET_LOWER));
            } else if (RUSSIAN_ALPHABET_UPPER.indexOf(c) != -1) {
                encrypted.append(shiftChar(c, key, RUSSIAN_ALPHABET_UPPER));
            } else if (RUSSIAN_ALPHABET_LOWER.indexOf(c) != -1) {
                encrypted.append(shiftChar(c, key, RUSSIAN_ALPHABET_LOWER));
            } else {
                encrypted.append(c);
            }
        }

        return encrypted.toString();
    }

    public String decrypt(String input, int key) {
        return encrypt(input, -key);
    }

    private char shiftChar(char c, int key, String alphabet) {
        int index = alphabet.indexOf(c);
        int newIndex = (index + key) % alphabet.length();

        if (newIndex < 0) {
            newIndex += alphabet.length();
        }

        return alphabet.charAt(newIndex);
    }
}
