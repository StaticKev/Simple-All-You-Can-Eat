package AllYouCanEat.Repository.StaffDAO;

import AllYouCanEat.Entity.Staff.Customer;
import AllYouCanEat.Repository.Contract.CustomerDAO;
import AllYouCanEat.Values.FindBy;

import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    private final DataSource dataSource;

    public CustomerDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void updateDetail(Customer item) {

        try (Connection connection = dataSource.getConnection()) {

            String sql = """
                    UPDATE customer SET name = ?, email = ?, phone = ? WHERE customerID = ?;
                    """;

            try (PreparedStatement prepStat = connection.prepareStatement(sql, Statement.NO_GENERATED_KEYS)) {
                prepStat.setString(1, item.name());
                prepStat.setString(2, item.email());
                prepStat.setString(3, item.phone());
                prepStat.setInt(4, item.customerId());

                prepStat.executeUpdate();

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Customer> retrieveCustomer(Customer tempCustomer, FindBy findBy) {
        List<Customer> customers = new LinkedList<>();

        try (Connection connection = dataSource.getConnection()) {

            String sql = """
                    SELECT * FROM customer WHERE %s LIKE ?;
                    """;

            String value = "";

            if (findBy.equals(FindBy.NAME)) {
                sql = String.format(sql, "name");
                value = tempCustomer.name();
            } else if (findBy.equals(FindBy.EMAIL)) {
                sql = String.format(sql, "email");
                value = tempCustomer.email();
            } else if (findBy.equals(FindBy.PHONE)) {
                sql = String.format(sql, "phone");
                value = tempCustomer.phone();
            }

            try (PreparedStatement prepStat = connection.prepareStatement(sql)) {

                prepStat.setString(1, "%" + value + "%");

                try (ResultSet resultSet = prepStat.executeQuery()) {
                    while (resultSet.next()) {
                        customers.add(new Customer(
                                resultSet.getInt("customerID"),
                                resultSet.getString("name"),
                                resultSet.getString("email"),
                                resultSet.getString("phone")
                        ));
                    }
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return customers;
    }

    @Override
    public int insertItem(Customer item) {

        int lastID = 0;

        try (Connection connection = dataSource.getConnection()) {

            String sql = """
                    INSERT INTO customer (name, email, phone) values (?, ?, ?);
                    """;

            try (PreparedStatement prepStat = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                prepStat.setString(1, item.name());

                if (item.email().equals("-")) {
                    prepStat.setNull(2, Types.VARCHAR);
                } else {
                    prepStat.setString(2, item.email());
                }

                if (item.phone().equals("-")) {
                    prepStat.setNull(3, Types.VARCHAR);
                } else {
                    prepStat.setString(3, item.phone());
                }

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
