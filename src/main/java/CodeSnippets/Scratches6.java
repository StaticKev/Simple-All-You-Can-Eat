package CodeSnippets;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Scratches6 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int userInput = 0; // Initialize with a default value

        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Enter an integer: ");
                userInput = scanner.nextInt();
                validInput = true; // Exit the loop if the input is valid
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine(); // Consume the invalid input
            }
        }

        System.out.println("You entered: " + userInput);
        // Now you can use userInput in your code.

    }
}
