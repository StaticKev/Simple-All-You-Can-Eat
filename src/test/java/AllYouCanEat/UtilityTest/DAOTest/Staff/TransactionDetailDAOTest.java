package AllYouCanEat.UtilityTest.DAOTest.Staff;

import AllYouCanEat.Entity.Company.Package;
import AllYouCanEat.Entity.Staff.TransactionDetail;
import AllYouCanEat.Repository.StaffDAO.TransactionDetailDAO;
import AllYouCanEat.Utility.MyConnection;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

public class TransactionDetailDAOTest {

    @Test
    void insertionTest() {

        DataSource dataSource = MyConnection.getDataSource();

        TransactionDetail tscDetail = new TransactionDetail();

        // Di sini `package` nya ngak sesuai makanya gak bisa masuk
        // Setelah itu karena transaction detail ndak bisa masuk, transaction juga ikut ndak
        // bisa masuk.
        tscDetail.setPackageType(new Package("INDIVIDUAL", 150000,1));
        tscDetail.setInitialPrice(320000);
        tscDetail.setChange(10000);
        tscDetail.setPayment(23425);
        tscDetail.setChange(3425);
        tscDetail.setTotal(14525552);

        TransactionDetailDAO transactionDAO = new TransactionDetailDAO(dataSource);

        System.out.println(transactionDAO.insertItem(tscDetail));

    }

}
