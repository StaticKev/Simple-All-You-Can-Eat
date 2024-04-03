package AllYouCanEat.Repository.CompanyDAO;

import AllYouCanEat.Entity.Company.Cashier;
import AllYouCanEat.Repository.Contract.CompanyRecord;
import AllYouCanEat.Values.Shift;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class CashierDAO implements CompanyRecord<Cashier>{

    private final DataSource dataSource;

    public CashierDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Cashier> retrieveValues() {
        List<Cashier> cashiers = new LinkedList<>();

        try (Connection connection = dataSource.getConnection()) {

            String sql = """
                    SELECT * FROM cashier
                    """;

            try (Statement statement = connection.createStatement()) {

                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    while (resultSet.next()) {

                        cashiers.add(new Cashier(
                                resultSet.getInt("cashierID"),
                                resultSet.getString("cashierName"),
                                Shift.valueOf(resultSet.getString("shift"))
                        ));

                    }

                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        return cashiers;
    }

}
