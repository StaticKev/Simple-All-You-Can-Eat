package AllYouCanEat.Service.StaffService.SubService;

import AllYouCanEat.Entity.Staff.TimeRecord;
import AllYouCanEat.Entity.Staff.Violation;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ViolationChecker {

    public void violationCheck(Violation violation, TimeRecord timeRecord) {

        boolean check = true;
        int choice = 0;
        Scanner scanner = new Scanner(System.in);

        do {
            try {
                System.out.println("Leftover?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> {
                        violation.setTypeA(true);
                        check = false;
                    }
                    case 2 -> {
                        violation.setTypeA(false);
                        check = false;
                    }
                    default -> System.out.println("INDEX OUT OF RANGE");
                }

            } catch (InputMismatchException e1) {
                System.out.println("UNEXPECTED INPUT");
                scanner.nextLine();
            } catch (NoSuchElementException e2) {
                scanner.nextLine();
            }
        } while (check);
        check = true;

        if (violation.isTypeA()) {
            do {
                try {
                    System.out.println("Weight: ");
                    choice = scanner.nextInt();
                    violation.setWeight(choice);
                    check = false;
                } catch (InputMismatchException e) {
                    System.out.println("INVALID INPUT");
                    scanner.nextLine();
                }
            } while (check);
        } else {
            violation.setWeight(0);
        }
        check = true;

        long tempDuration = Duration.between(timeRecord.getBegin(), timeRecord.getEnd()).toMinutes();

        // Simplified if-else?
        violation.setTypeB(tempDuration > Duration.ofMinutes(90).toMinutes());

        if (violation.isTypeB()) {
            violation.setDurationV(tempDuration - Duration.ofMinutes(90).toMinutes());
        } else {
            violation.setDurationV(0);
        }

        do {
            try {
                System.out.println("Equipment break?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> {
                        violation.setTypeC(true);
                        check = false;
                    }
                    case 2 -> {
                        violation.setTypeC(false);
                        check = false;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("INVALID INPUT");
                scanner.nextLine();
            }
        } while (check);
        check = true;

        if (violation.isTypeC()) {
            do {
                try {
                    System.out.println("Amount: ");
                    choice = scanner.nextInt();
                    violation.setAmount(choice);
                    check = false;
                } catch (InputMismatchException e) {
                    throw new RuntimeException(e);
                }
            } while (check);
        } else {
            violation.setAmount(choice);
        }

    }

}