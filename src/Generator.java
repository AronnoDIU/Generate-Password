import java.util.Scanner;

public class Generator {
    Alphabet alphabet; // Create an Alphabet object.
    public static Scanner userINPUT; // Create a Scanner object.

    public Generator(Scanner userINPUT) {
        Generator.userINPUT = userINPUT;
    }

    public Generator(boolean IncludeUpperCase, boolean IncludeLowerCase,
                     boolean IncludeNumericCase, boolean IncludeSpecialCase) {

        alphabet = new Alphabet(IncludeUpperCase, IncludeLowerCase,
                IncludeNumericCase, IncludeSpecialCase);
    }

    public void mainUserWindow() {
        System.out.println("Welcome to Aronno's Generate Random Password Services :)");
        displayMainMenu();

        String userOption = "-1"; // Default value(Initialize with invalid value).

        while (!userOption.equals("4")) { // While userOption is not equal to 4.

            userOption = userINPUT.next(); // Get userOption from user input.

            switch (userOption) { // Switch on userOption value to perform different actions.

                case "1" -> {
                    passwordGenerator(); // Call passwordGenerator method and pass userINPUT.
                    displayMainMenu(); // Call displayMainMenu method.
                }
                case "2" -> {
                    checkPasswordStrength(); // Call checkPassword method and pass userINPUT.
                    displayMainMenu();
                }
                case "3" -> {
                    printGuidelineToGeneratePassword();
                    displayMainMenu();
                }
                case "4" -> printQuitMessage(); // Call printQuitMessage method.

                default -> { // If userOption is not equal to 1, 2, 3 or 4.
                    System.out.println();
                    System.out.println("Invalid input please try again with a valid option.");
                    displayMainMenu();
                }
            }
        }
    }

    private Password GeneratePassword(int length) {
        final StringBuilder containPassword = new StringBuilder();

        final int alphabetLength = alphabet.getAlphabet().length();

        int Maximum = alphabetLength - 1;
        int Minimum = 0;
        int Range = Maximum - Minimum + 1;

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * Range) + Minimum;
            containPassword.append(alphabet.getAlphabet().charAt(index));
        }
        return new Password(containPassword.toString());
    }

    private void printGuidelineToGeneratePassword() {
        System.out.println();
        System.out.println("Use a minimum password length of 8 or more characters if permitted");
        System.out.println("Include lowercase and uppercase alphabetic characters, " +
                "numbers and symbols if permitted");
        System.out.println("Generate passwords randomly where feasible");
        System.out.println("Avoid using the same password twice (e.g., across multiple " +
                "user accounts and/or software systems)");
        System.out.println("Avoid character repetition, keyboard patterns, " +
                "dictionary words, letter or number sequences," +
                "\nusernames, relative or pet names, romantic links (current or past) " +
                "and biographical information (e.g., ID numbers, ancestors' names or dates).");
        System.out.println("Avoid using information that the user's colleagues and/or " +
                "acquaintances might know to be associated with the user");
        System.out.println("Do not use passwords which consist wholly of any simple " +
                "combination of the aforementioned weak components");
    }

    private boolean isInclude(String Input) {
        return Input.equalsIgnoreCase("Y");
    }

    private void passwordGenerator() {
        boolean IncludeUpperCase = false;
        boolean IncludeLowerCase = false;
        boolean IncludeNumericCase = false;
        boolean IncludeSpecialCase = false;

        boolean isParameterCorrect;

        System.out.println();
        System.out.println("""
                Hello, welcome to the Generate Random Password Service.\s
                To answer the following questions by 'Y' for Yes +
                or 'N' for No to make a request for a random password.""");

        System.out.println();

        do {
            String input; // Initialize input.
            isParameterCorrect = false; // Reset isParameterCorrect.

            do {
                System.out.println("Do you want Lowercase letters \"abcd...\" to be used? ");
                input = userINPUT.next();
                InputErrorCase(input); // Check if input is Y or N. Otherwise throw an error.
            } while (!input.equalsIgnoreCase("Y")
                    && !input.equalsIgnoreCase("N"));

            if (isInclude(input)) IncludeLowerCase = true; // Update IncludeLowerCase.

            do {
                System.out.println("Do you want Uppercase letters \"ABCD...\" to be used? ");
                input = userINPUT.next();
                InputErrorCase(input);
            } while (!input.equalsIgnoreCase("Y")
                    && !input.equalsIgnoreCase("N"));

            if (isInclude(input)) IncludeUpperCase = true; // Update IncludeUpperCase.

            do {
                System.out.println("Do you want Numbers \"1234...\" to be used? ");
                input = userINPUT.next();
                InputErrorCase(input);
            } while (!input.equalsIgnoreCase("Y")
                    && !input.equalsIgnoreCase("N"));

            if (isInclude(input)) IncludeNumericCase = true; // Update IncludeNumericCase.

            do {
                System.out.println("Do you want Special Characters \"!@#$...\" to be used? ");
                input = userINPUT.next();
                InputErrorCase(input);
            } while (!input.equalsIgnoreCase("Y")
                    && !input.equalsIgnoreCase("N"));

            if (isInclude(input)) IncludeSpecialCase = true; // Update IncludeSpecialCase.

            // Check if no parameters are selected, ask the user to select at least one.
            if (!IncludeUpperCase && !IncludeLowerCase
                    && !IncludeNumericCase && !IncludeSpecialCase) {
                System.out.println("You have selected no characters to generate your " +
                        "password, at least one of your answers should be Yes\n");
                isParameterCorrect = true; // Update isParameterCorrect.
            }

        } while (isParameterCorrect); // Keep asking the user to select at least one parameter.

        System.out.println("Great! Now enter the length of the password");
        int length = userINPUT.nextInt(); // Get the length of the password.

        final Generator generator = new Generator(IncludeUpperCase,
                IncludeLowerCase, IncludeNumericCase, IncludeSpecialCase);

        final Password password = generator.GeneratePassword(length);

        System.err.println("Your generated password -> " + password);
    }

    private void InputErrorCase(String i) {
        if (!i.equalsIgnoreCase("Y")
                && !i.equalsIgnoreCase("N")) {
            System.out.println
                    ("Please enter 'Y' or 'N' only for Yes or No respectively. \n");
        }
    }

    private void checkPasswordStrength() {
        String inputPassword;

        System.out.print("\nEnter your password: ");
        inputPassword = userINPUT.next();

        final Password checkPassword = new Password(inputPassword); // Create a Password object.

        System.out.println
                (checkPassword.calculateScore()); // Calculate Password Score and display it.
    }

    private void displayMainMenu() {  // Prints the main menu of the program.
        System.out.println();
        System.out.println("Enter 1 - Generate Random Password");
        System.out.println("Enter 2 - Password Strength Check");
        System.out.println("Enter 3 - Useful Information for Passwords");
        System.out.println("Enter 4 - Quit the Program");
        System.out.print("Choice: ");
    }

    private void printQuitMessage() {
        System.out.println("Closing the program! Thank you for using the services!");
    }
}
