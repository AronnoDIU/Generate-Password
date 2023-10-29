import java.util.Scanner;

/**
 * @author Yeasir Arafat Aronno
 */

public class Main {
    public static final Scanner
            userINPUT = new Scanner(System.in); // Create a Scanner object.

    public static void main(String[] args) {
        Generator generator = new Generator(userINPUT); // Create a Generator object.
        generator.mainUserWindow(); // Call the mainLoop method.
        userINPUT.close(); // Close the Scanner object.
    }
}