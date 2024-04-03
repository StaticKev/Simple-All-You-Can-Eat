package AllYouCanEat.UtilityTest.UtilityTest;

import AllYouCanEat.Utility.MyConnection;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionTest {

    @Test
    void connectionTest() throws SQLException {

        Connection connection = MyConnection.getDataSource().getConnection();
        connection.close();

    }

}
