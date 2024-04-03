package AllYouCanEat.Repository.StaffDAO;

import AllYouCanEat.Entity.Staff.Transaction;
import AllYouCanEat.Repository.Contract.OrderDAO;

import javax.sql.DataSource;
import java.sql.*;

public class TransactionDAO implements OrderDAO<Transaction> {

    private final DataSource dataSource;

    public TransactionDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int insertItem(Transaction item) {

        int lastID = 0;

        try (Connection connection = dataSource.getConnection()) {

            String sql = """
                    INSERT INTO `transaction` (transactionTime, tscDetailID, pmID, cashierID)
                    VALUES (?, ?, ?, ?);
                    """;

            try (PreparedStatement prepStat = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                prepStat.setTimestamp(1, Timestamp.valueOf(item.getTransactionTime()));
                prepStat.setInt(2, item.getTransactionDetail().gettDetailId());
                prepStat.setInt(3, item.getPaymentMethod().pmID());
                prepStat.setInt(4, item.getCashier().cashierId());

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
