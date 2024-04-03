package AllYouCanEat.Repository.StaffDAO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TableOccupationDAO {

    private final DataSource dataSource;

    public TableOccupationDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean insertItem(int orderId, List<String> tableOccupation) {
        boolean isSuccess = false;

        try (Connection connection = dataSource.getConnection()) {

            String sql = """
                    INSERT INTO occupied_table (orderID, tableID) VALUES (?, ?)
                    """;

            try (PreparedStatement prepStat = connection.prepareStatement(sql)) {

                for (String s : tableOccupation) {
                    prepStat.clearParameters();
                    prepStat.setInt(1, orderId);
                    prepStat.setString(2, s);
                    prepStat.addBatch();

                }

                prepStat.executeBatch();
                isSuccess = true;

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

        return isSuccess;
    }

}
