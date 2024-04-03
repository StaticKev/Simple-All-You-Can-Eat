package AllYouCanEat.UtilityTest.DAOTest.Company;

import AllYouCanEat.Entity.Company.Table;
import AllYouCanEat.Repository.CompanyDAO.TableDAO;
import AllYouCanEat.Utility.MyConnection;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.util.List;

public class TableDAOTest {

    @Test
    void tableDAOTest() {

        DataSource dataSource = MyConnection.getDataSource();

        TableDAO tableDAO = new TableDAO(dataSource);

        List<Table> tables = tableDAO.retrieveValues();

        for (Table t : tables) {
            System.out.println("------------------------------------");
            System.out.println(
                    "ID = " + t.tableId() + "\nCapacity = " + t.availableSeat() + "\nBlock = " + t.tableBlock()
            );
        }

    }

}
