package AllYouCanEat.View.TempView;

import AllYouCanEat.Entity.Company.PaymentMethod;
import AllYouCanEat.Values.AvailablePaymentMethod;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FinishOrderView {

    private FinishOrderView(){}

    public static int obtainPayment() {
        Scanner scanner = new Scanner(System.in);
        int payment;

        while (true) {

            try {
                System.out.println("Input Payment: ");
                payment = scanner.nextInt();
                break;

            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                scanner.next();
            }
        }

        return payment;
    }

    public static PaymentMethod getPm(List<PaymentMethod> pm) {

        AvailablePaymentMethod choosenApm = null;
        System.out.println("Available Payment Methods: ");
        for (int i = 0; i < pm.size(); i++) {
            System.out.println(i + 1 + ". " + pm.get(i).availablePaymentMethod() + "; " +
                    pm.get(i).merchant().merchantName() + "(" + pm.get(i).merchant().discountValue() + ")");
        }

        boolean check = true;
        int choosenNumber = 0;

        Scanner scanner = new Scanner(System.in);

        while (check) {
            try {
                System.out.println("Pick payment method: ");
                choosenNumber = scanner.nextInt();

                if (choosenNumber <= pm.size() && !(choosenNumber < 1)) {
                    check = false;
//                    scanner.close();
                } else {
                    System.out.println("INDEX OUT OF RANGE");
                }

            } catch (InputMismatchException e) {
                System.out.println("UNEXPECTED INPUT");
                scanner.nextLine();
            }
        }

        return pm.get(choosenNumber - 1);
    }

}
