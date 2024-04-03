package AllYouCanEat.UtilityTest.DAOTest.Company;

import AllYouCanEat.Repository.CompanyDAO.CashierDAO;
import AllYouCanEat.Utility.MyConnection;
import org.junit.jupiter.api.Test;

import AllYouCanEat.Entity.Company.Cashier;

import javax.sql.DataSource;
import java.util.List;

public class CashierDAOTest {

    @Test
    void cashierDAOTest() {

        DataSource dataSource = MyConnection.getDataSource();

        CashierDAO cashierDAO = new CashierDAO(dataSource);

        List<Cashier> cashiers = cashierDAO.retrieveValues();

        System.out.println("Record retrieved = " + cashiers.size());

        for (Cashier cashier : cashiers) {
            System.out.println(
                    "ID = " + cashier.cashierId()
                            + "; Name = " + cashier.cashierName() +
                            "; Shift = " + cashier.shift()
            );
        }

    }

}
