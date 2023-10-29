public class Alphabet {
    public static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    public static final String NUMERIC_NUMBERS = "1234567890";
    public static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+\\/~?";

    private final StringBuilder storeCharacters; // Create a StringBuilder object.


    public Alphabet(boolean uppercaseIncluded, boolean lowercaseIncluded,
                    boolean numericIncluded, boolean specialCharactersIncluded) {

        storeCharacters = new StringBuilder();

        if (uppercaseIncluded) storeCharacters.append(UPPERCASE_LETTERS);
        if (lowercaseIncluded) storeCharacters.append(LOWERCASE_LETTERS);
        if (numericIncluded) storeCharacters.append(NUMERIC_NUMBERS);
        if (specialCharactersIncluded) storeCharacters.append(SPECIAL_CHARACTERS);
    }

    public String getAlphabet() {
        return storeCharacters.toString();
    }
}
