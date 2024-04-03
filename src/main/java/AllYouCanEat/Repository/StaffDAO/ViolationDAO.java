package AllYouCanEat.Repository.StaffDAO;

import AllYouCanEat.Entity.Staff.Violation;
import AllYouCanEat.Repository.Contract.OrderDAO;

import javax.sql.DataSource;
import java.sql.*;

public class ViolationDAO implements OrderDAO<Violation> {

    private final DataSource dataSource;

    public ViolationDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int insertItem(Violation item) {

        int lastID = 0;

        try (Connection connection = dataSource.getConnection()) {

            String sql = """
                    INSERT INTO violation (typeA, weight_gram, typeB, exceededTime_min, typeC, amount)
                    VALUES (?, ?, ?, ?, ?, ?);
                    """;

            try (PreparedStatement prepStat = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                prepStat.setBoolean(1, item.isTypeA());
                prepStat.setBoolean(3, item.isTypeB());
                prepStat.setBoolean(5, item.isTypeC());

                if (item.isTypeA()) {
                    prepStat.setInt(2, item.getWeight());
                } else {
                    prepStat.setNull(2, Types.SMALLINT);
                }

                if (item.isTypeB()) {
                    prepStat.setLong(4, item.getDurationV());
                } else {
                    prepStat.setNull(4, Types.BIGINT);
                }

                if (item.isTypeC()) {
                    prepStat.setInt(6, item.getAmount());
                } else {
                    prepStat.setNull(6, Types.TINYINT);
                }

                prepStat.executeUpdate();
                ResultSet resultSet = prepStat.getGeneratedKeys();

                if (resultSet.next()) {
                    lastID = resultSet.getInt(1);
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException (e);

        }

        return lastID;
    }

}
