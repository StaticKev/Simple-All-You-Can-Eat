package AllYouCanEat.Repository.StaffDAO;

import AllYouCanEat.Entity.Staff.TransactionDetail;
import AllYouCanEat.Repository.Contract.OrderDAO;

import javax.sql.DataSource;
import java.sql.*;

public class TransactionDetailDAO implements OrderDAO<TransactionDetail> {


    private final DataSource dataSource;

    public TransactionDetailDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public int insertItem(TransactionDetail item) {

        int lastID = 0;

        try (Connection connection = dataSource.getConnection()) {

            String sql = """
                    INSERT INTO transaction_detail (packageType, initialPrice, tax, total, payment, changeAmount, violationCharge, discount)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?);
                    """;

            try (PreparedStatement prepStat = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                prepStat.setString(1, item.getPackageType().packageType());
                prepStat.setInt(2, item.getInitialPrice());
                prepStat.setInt(3, item.getTax());
                prepStat.setInt(4, item.getTotal());
                prepStat.setInt(5, item.getPayment());
                prepStat.setInt(6, item.getChange());
                prepStat.setInt(7, item.getViolationCharge());
                prepStat.setInt(8, item.getDiscount());

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
