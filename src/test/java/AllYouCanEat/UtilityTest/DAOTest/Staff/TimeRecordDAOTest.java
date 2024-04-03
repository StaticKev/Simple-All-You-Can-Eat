package AllYouCanEat.UtilityTest.DAOTest.Staff;

import AllYouCanEat.Entity.Staff.TimeRecord;
import AllYouCanEat.Repository.StaffDAO.TimeRecordDAO;
import AllYouCanEat.Utility.MyConnection;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.time.LocalDateTime;

public class TimeRecordDAOTest {

    @Test
    void insertItemTest() {

        DataSource dataSource = MyConnection.getDataSource();

        TimeRecordDAO timeRecordDAO = new TimeRecordDAO(dataSource);

        TimeRecord timeRecord = new TimeRecord(LocalDateTime.now());
        timeRecord.setEnd(LocalDateTime.now());

        System.out.println(timeRecordDAO.insertItem(timeRecord));

    }
}
