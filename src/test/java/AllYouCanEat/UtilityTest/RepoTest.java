package AllYouCanEat.UtilityTest;

import AllYouCanEat.Entity.Company.*;
import AllYouCanEat.Entity.Company.Package;
import AllYouCanEat.Entity.Contract.Company;
import AllYouCanEat.Entity.Staff.Customer;
import AllYouCanEat.Entity.Staff.Order;
import AllYouCanEat.Repository.CompanyDAO.CashierDAO;
import AllYouCanEat.Repository.Contract.OrderRepo;
import AllYouCanEat.Repository.OrderRepoImpl;
import AllYouCanEat.Utility.MyConnection;
import AllYouCanEat.Values.AvailablePaymentMethod;
import AllYouCanEat.Values.Shift;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RepoTest {

    @Test
    void createOrderTest() {

        DataSource dataSource = MyConnection.getDataSource();

        OrderRepo orderRepo = new OrderRepoImpl(dataSource);

        Order order = new Order(
                new Customer(1, "asfa", "asf", "afs"),
                120,
                LocalDateTime.now()
        );

        order.getTimeRecord().setTimeRecordId(1);
        order.getViolation().setViolationID(1);
        order.getTransaction().getTransactionDetail().setDetailId(5);
        order.getTransaction().setCashier(new Cashier(1, "Caca Clarissa", Shift.NIGHT_SHIFT));
        order.getTransaction().getTransactionDetail().setPackageType(new Package("INDIVIDUAL", 150000,1));
        order.getTransaction().setPaymentMethod(new PaymentMethod(6, new Merchant("Kev", 12.00f), AvailablePaymentMethod.CASH));

        order.getTimeRecord().setEnd(LocalDateTime.now());
        order.getTransaction().setTransactionTime(LocalDateTime.now());
        List<String> list = List.of("T05", "T04", "T03", "T23");
        order.getTransaction().setTableOccupation(list);

        orderRepo.createOrder(order);

    }

    @Test
    void companyRecordRetrievalTest() {

        DataSource dataSource = MyConnection.getDataSource();
        OrderRepo orderRepo = new OrderRepoImpl(dataSource);

        List<Cashier> cashiers = new ArrayList<>();
        List<Table> tables = new ArrayList<>();
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        List<Package> packages = new ArrayList<>();

        List<List<? extends Company>> companyRecord = orderRepo.retrieveCompanyRecord();

        for (List<? extends Company> list : companyRecord) {

            if (!list.isEmpty() && list.get(0) instanceof Cashier) {
                for (Company c : list) {
                    cashiers.add((Cashier) c);
                }
            } else if (!list.isEmpty() && list.get(0) instanceof Table) {
                for (Company c : list) {
                    tables.add((Table) c);
                }
            } else if (!list.isEmpty() && list.get(0) instanceof PaymentMethod) {
                for (Company c : list) {
                    paymentMethods.add((PaymentMethod) c);
                }
            } else if (!list.isEmpty() && list.get(0) instanceof Package) {
                for (Company c : list) {
                    packages.add((Package) c);
                }
            }

        }

        for (Cashier c : cashiers) {
            System.out.println(c);
        }
        System.out.println("--------------------------");
        for (Table t : tables) {
            System.out.println(t);
        }
        System.out.println("--------------------------");
        for (PaymentMethod p : paymentMethods) {
            System.out.println(p);
        }
        System.out.println("--------------------------");
        for (Package pk : packages) {
            System.out.println(pk);
        }

    }

}
