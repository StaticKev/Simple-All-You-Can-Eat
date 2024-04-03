package AllYouCanEat.Repository.StaffDAO;

import AllYouCanEat.Entity.Staff.Order;
import AllYouCanEat.Repository.Contract.OrderDAO;

import javax.sql.DataSource;
import java.sql.*;

public class OrderDAOImpl implements OrderDAO<Order> {

    private final DataSource dataSource;

    public OrderDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public int insertItem(Order item) {

        int lastID = 0;

        try (Connection connection = dataSource.getConnection()) {

            String sql = """
                    INSERT INTO `order` (customerID, personCount, timeID, violationID, transactionID)
                    VALUES (?, ?, ?, ?, ?);
                    """;

            try (PreparedStatement prepStat = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                prepStat.setInt(1, item.getCustomer().customerId());
                prepStat.setInt(2, item.getPersonCount());
                prepStat.setInt(3, item.getTimeRecord().getTimeRecordId());
                prepStat.setInt(4, item.getViolation().getViolationID());
                prepStat.setInt(5, item.getTransaction().getTransactionID());

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
