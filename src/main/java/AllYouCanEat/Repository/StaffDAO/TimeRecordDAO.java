package AllYouCanEat.Repository.StaffDAO;

import AllYouCanEat.Entity.Staff.TimeRecord;
import AllYouCanEat.Repository.Contract.OrderDAO;

import javax.sql.DataSource;
import java.sql.*;

public class TimeRecordDAO implements OrderDAO<TimeRecord> {

    private final DataSource dataSource;

    public TimeRecordDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int insertItem(TimeRecord item) {

        int lastID = 0;

        try (Connection connection = dataSource.getConnection()) {

            String sql = """
                    INSERT INTO time_record (begin, end) VALUES (?, ?)
                    """;

            try (PreparedStatement prepStat = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                prepStat.setTimestamp(1, Timestamp.valueOf(item.getBegin()));
                prepStat.setTimestamp(2, Timestamp.valueOf(item.getEnd()));

                prepStat.executeUpdate();

                ResultSet resultSet = prepStat.getGeneratedKeys();

                if (resultSet.next()) {
                    lastID = resultSet.getInt(1);
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

        return lastID;

    }

}
