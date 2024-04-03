package AllYouCanEat.Repository.CompanyDAO;

import AllYouCanEat.Entity.Company.Table;
import AllYouCanEat.Repository.Contract.CompanyRecord;
import AllYouCanEat.Values.TableBlock;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class TableDAO implements CompanyRecord<Table> {

    private final DataSource dataSource;

    public TableDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Table> retrieveValues() {
        List<Table> tables = new LinkedList<>();

        try (Connection connection = dataSource.getConnection()) {

            String sql = """
                    SELECT * FROM `table`
                    """;

            try (Statement statement = connection.createStatement()) {

                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    while(resultSet.next()) {

                        tables.add(new Table(
                                resultSet.getString("tableID"),
                                resultSet.getInt("availableSeat"),
                                TableBlock.valueOf(resultSet.getString("tableBlock"))
                                ));
                    }

                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tables;
    }
}
