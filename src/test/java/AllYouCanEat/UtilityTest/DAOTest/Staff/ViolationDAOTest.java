package AllYouCanEat.UtilityTest.DAOTest.Staff;

import AllYouCanEat.Entity.Staff.Violation;
import AllYouCanEat.Repository.StaffDAO.ViolationDAO;
import AllYouCanEat.Utility.MyConnection;
import org.junit.jupiter.api.Test;
import javax.sql.DataSource;

public class ViolationDAOTest {

    @Test
    void insertionTest() {

        DataSource dataSource = MyConnection.getDataSource();

        ViolationDAO violationDAO = new ViolationDAO(dataSource);

        Violation violation = new Violation();

        violation.setTypeA(true);
        violation.setTypeB(false);
        violation.setTypeC(true);

        violation.setWeight(13);
        violation.setAmount(2);

        System.out.println(violationDAO.insertItem(violation));


    }

}
