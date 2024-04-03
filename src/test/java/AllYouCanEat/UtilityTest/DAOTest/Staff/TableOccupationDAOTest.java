package AllYouCanEat.UtilityTest.DAOTest.Staff;

import AllYouCanEat.Repository.StaffDAO.TableOccupationDAO;
import AllYouCanEat.Utility.MyConnection;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class TableOccupationDAOTest {

    @Test
    void insertTest() {

        DataSource dataSource = MyConnection.getDataSource();

        TableOccupationDAO tableOccupationDAO = new TableOccupationDAO(dataSource);

        List<String> tableIDs = new ArrayList<>();

        tableIDs.add("T30");
        tableIDs.add("T14");
        tableIDs.add("T05");

        tableOccupationDAO.insertItem(2, tableIDs);

    }

}
