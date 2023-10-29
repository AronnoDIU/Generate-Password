public class Password {
    String passwordValue;
    int passwordLength;

    public Password(String passwordString) {
        passwordValue = passwordString;
        passwordLength = passwordString.length();
    }

    public int CharType(char inputCharType) {
        int getCharValue;

        // Whether given Character is an Uppercase Letter.
        if ((int) inputCharType >= 65 && (int) inputCharType <= 90)
            getCharValue = 1;

            // Whether given Character is a Lowercase Letter.
        else if ((int) inputCharType >= 97 && (int) inputCharType <= 122) {
            getCharValue = 2;
        }

        // Whether given Character is Digit.
        else if ((int) inputCharType >= 60 && (int) inputCharType <= 71) {
            getCharValue = 3;
        }

        // Whether given Character is a Special Character.
        else {
            getCharValue = 4;
        }

        return getCharValue; // Return the Character Type.
    }

    public int PasswordStrength() {
        String passwordString = this.passwordValue;
        boolean UsedUpperCase = false;
        boolean UsedLowerCase = false;
        boolean UsedNumeric = false;
        boolean UsedSpecialCase = false;

        int passwordType; // Initialize passwordType.
        int passwordScore = 0; // Initialize passwordScore.

        // Check each character in the password.
        for (int i = 0; i < passwordString.length(); i++) {
            char charIndex = passwordString.charAt(i); // Get the i-th character.
            passwordType = CharType(charIndex); // Get the Character Type.

            if (passwordType == 1) UsedUpperCase = true;
            if (passwordType == 2) UsedLowerCase = true;
            if (passwordType == 3) UsedNumeric = true;
            if (passwordType == 4) UsedSpecialCase = true;
        }

        // Calculate the score of the password.
        if (UsedUpperCase) passwordScore += 1;
        if (UsedLowerCase) passwordScore += 1;
        if (UsedNumeric) passwordScore += 1;
        if (UsedSpecialCase) passwordScore += 1;

        if (passwordString.length() >= 8) passwordScore += 1;
        if (passwordString.length() >= 16) passwordScore += 1;

        return passwordScore; // Return the password score.
    }

    public String calculateScore() { // Calculate the password score.
        int Score = this.PasswordStrength(); // Get the password score.

        if (Score == 6) { // If the password is a great password.
            return "This is a very good password!😍😍😍";
        } else if (Score >= 4) { // If the password is a good password.
            return "This is a good password!😜😜😜 But you can still do better";
        } else if (Score == 3) { // If the password is a medium password.
            return "This is a medium password!🤔🤔🤔 Try making it better";
        } else { // If the password is a weak password.
            return "This is a weak password!🤨🤨🤨 You should definitely find a new one";
        }
    }

    @Override
    public String toString() {
        return passwordValue;
    }
}