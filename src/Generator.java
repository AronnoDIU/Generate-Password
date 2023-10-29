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
        System.out.println("Welcome to Aronno's Password Services :)");
        printMainMenu();

        String userOption = "-1"; // Default value(Initialize with invalid value).

        while (!userOption.equals("4")) { // While userOption is not equal to 4.

            userOption = userINPUT.next(); // Get userOption from user input.

            switch (userOption) { // Switch on userOption value to perform different actions.

                case "1" -> {
                    passwordGenerator(); // Call requestPassword method and pass userINPUT.
                    printMainMenu(); // Call printMenu method.
                }
                case "2" -> {
                    checkPassword(); // Call checkPassword method and pass userINPUT.
                    printMainMenu();
                }
                case "3" -> {
                    printGuidelineToGeneratePassword(); // Call printUsefulInfo method.
                    printMainMenu();
                }
                case "4" -> printQuitMessage(); // Call printQuitMessage method.
                default -> { // If userOption is not equal to 1, 2, 3 or 4.
                    System.out.println();
                    System.out.println("Kindly select one of the available commands");
                    printMainMenu();
                }
            }
        }
    }

    private Password GeneratePassword(int length) {
        final StringBuilder password = new StringBuilder();

        final int alphabetLength = alphabet.getAlphabet().length();

        int Maximum = alphabetLength - 1;
        int Minimum = 0;
        int Range = Maximum - Minimum + 1;

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * Range) + Minimum;
            password.append(alphabet.getAlphabet().charAt(index));
        }
        return new Password(password.toString());
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

    private void passwordGenerator() {
        boolean IncludeUpperCase = false;
        boolean IncludeLowerCase = false;
        boolean IncludeNumericCase = false;
        boolean IncludeSpecialCase = false;

        boolean correctParams;

        System.out.println();
        System.out.println("""
                Hello, welcome to the Generate Password Service.\s
                To answer the following questions by 'Y' for Yes +
                or 'N' for No to make a request for a password.""");

        System.out.println();

        do {
            String input;
            correctParams = false;

            do {
                System.out.println("Do you want Lowercase letters \"abcd...\" to be used? ");
                input = userINPUT.next();
                PasswordRequestError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (isInclude(input)) IncludeLowerCase = true;

            do {
                System.out.println("Do you want Uppercase letters \"ABCD...\" to be used? ");
                input = userINPUT.next();
                PasswordRequestError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (isInclude(input)) IncludeUpperCase = true;

            do {
                System.out.println("Do you want Numbers \"1234...\" to be used? ");
                input = userINPUT.next();
                PasswordRequestError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (isInclude(input)) IncludeNumericCase = true;

            do {
                System.out.println("Do you want Symbols \"!@#$...\" to be used? ");
                input = userINPUT.next();
                PasswordRequestError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (isInclude(input)) IncludeSpecialCase = true;

            //No Pool Selected
            if (!IncludeUpperCase && !IncludeLowerCase && !IncludeNumericCase && !IncludeSpecialCase) {
                System.out.println("You have selected no characters to generate your " +
                        "password, at least one of your answers should be Yes\n");
                correctParams = true;
            }

        } while (correctParams);

        System.out.println("Great! Now enter the length of the password");
        int length = userINPUT.nextInt();

        final Generator generator = new Generator(IncludeUpperCase, IncludeLowerCase, IncludeNumericCase, IncludeSpecialCase);
        final Password password = generator.GeneratePassword(length);

        System.err.println("Your generated password -> " + password);
    }

    private boolean isInclude(String Input) {
        if (Input.equalsIgnoreCase("yes")) {
            return true;
        } else {
            return false;
        }
    }

    private void PasswordRequestError(String i) {
        if (!i.equalsIgnoreCase("yes") && !i.equalsIgnoreCase("no")) {
            System.out.println("You have entered something incorrect let's go over it again \n");
        }
    }

    private void checkPassword() {
        String input;

        System.out.print("\nEnter your password:");
        input = userINPUT.next();

        final Password p = new Password(input);

        System.out.println(p.calculateScore());
    }

    private void printMainMenu() {
        System.out.println();
        System.out.println("Enter 1 - Password Generator");
        System.out.println("Enter 2 - Password Strength Check");
        System.out.println("Enter 3 - Useful Information");
        System.out.println("Enter 4 - Quit");
        System.out.print("Choice:");
    }

    private void printQuitMessage() {
        System.out.println("Closing the program bye bye!");
    }
}
