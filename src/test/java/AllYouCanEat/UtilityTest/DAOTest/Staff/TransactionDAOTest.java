package AllYouCanEat.UtilityTest.DAOTest.Staff;

import AllYouCanEat.Entity.Company.Cashier;
import AllYouCanEat.Entity.Company.Merchant;
import AllYouCanEat.Entity.Company.PaymentMethod;
import AllYouCanEat.Entity.Staff.Transaction;
import AllYouCanEat.Repository.StaffDAO.TransactionDAO;
import AllYouCanEat.Utility.MyConnection;
import AllYouCanEat.Values.AvailablePaymentMethod;
import AllYouCanEat.Values.Shift;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TransactionDAOTest {

    @Test
    void insertionTest() {

        DataSource dataSource = MyConnection.getDataSource();

        TransactionDAO transactionDAO = new TransactionDAO(dataSource);

        // Ini untuk cashier, paymentMethod, dan transactionDetail harus sama dengan yang ada di database
        Transaction transaction = new Transaction();
        transaction.setTransactionTime(LocalDateTime.now());
        transaction.setCashier(new Cashier(1, "Caca Clarissa", Shift.NIGHT_SHIFT));
        transaction.setTableOccupation(new ArrayList<>());
        transaction.setPaymentMethod(new PaymentMethod(6, new Merchant("Kev", 12.00f), AvailablePaymentMethod.CASH));
        transaction.getTransactionDetail().setDetailId(5);

        System.out.println(transactionDAO.insertItem(transaction));

    }


}
