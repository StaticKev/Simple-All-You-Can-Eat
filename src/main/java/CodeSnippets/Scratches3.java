package CodeSnippets;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Scratches3 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int a = 0;

        boolean check = true;

        while (check) {
            try {
                System.out.println("Masukin sini: ");
                a = scanner.nextInt();
                check = false;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println(e);
            }
        }

        System.out.println(a);

    }

}
