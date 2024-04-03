package AllYouCanEat.View.TempView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NewOrderView {

    private NewOrderView() {}

    public static int fillAmount() {
        boolean success = false;
        int a = 0;
        int c = 0;

        while (!success) {
            try {
                if (c == 0) {
                    System.out.println("------------------------------");
                }
                System.out.println("Person count: ");
                Scanner scanner1 = new Scanner(System.in);
                a = scanner1.nextInt();
                success = true;
                scanner1.close();
                System.out.println("------------------------------");

            } catch (InputMismatchException e) {
                System.out.println("That's not a number!");

            }
            c++;
        }

        return a;
    }

//    public static PackageType packageDecision(List<PackageType> availableOption) {
//
//        int index = 1;
//        int choosedIndex = 0;
//        boolean success = false;
//        System.out.println("Available Package: ");
//        for (PackageType a : availableOption) {
//            System.out.println(index + ") " + a);
//            index++;
//        }
//
//        System.out.println();
//
//        while(!success) {
//            try {
//                Scanner scanner = new Scanner(System.in);
//                System.out.println("Choose Package(input number): ");
//                int tempInt = scanner.nextInt();
//                if (tempInt > 0 && tempInt <= availableOption.size()) {
//                    choosedIndex = tempInt;
//                    success = true;
//                } else {
//                    System.out.println("Match not found!");
//                }
//            } catch (InputMismatchException e) {
//                System.out.println("That's not a number!");
//            }
//        }
//
//        return availableOption.get(choosedIndex - 1);
//
//    }

    public static LocalDateTime beginOn() {
        LocalDate currentDate = LocalDate.now(); // 2023-04-20
        String hour = "";
        String minute = "";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd");
        String strf = "%02d";
        Scanner scanner = new Scanner(System.in);

        boolean success = false;
        while (!success) {
            try {
                scanner = new Scanner(System.in);
                System.out.println("Input hour: ");
                int num1 = scanner.nextInt();

                if (num1 >= 0 && num1 <= 23) {
                    hour = String.format(strf , num1);
                    success = true;
                } else {
                    System.out.println("Invalid time!");
                }

            } catch (InputMismatchException e) {
                System.out.println("That's not a number!");
            } finally {
                scanner.close();
            }
        }

        boolean success2 = false;
        while (!success2) {
            try {
                scanner = new Scanner(System.in);
                System.out.println("Input minute: ");
                int num1 = scanner.nextInt();

                if (num1 >= 0 && num1 <= 59) {
                    minute = String.format(strf , num1);
                    success2 = true;
                } else {
                    System.out.println("Invalid time!");
                }

            } catch (InputMismatchException e) {
                System.out.println("That's not a number!");
            }
        }

        return LocalDateTime.parse(hour + ":" + minute + " " + currentDate, dtf);
    }

}
