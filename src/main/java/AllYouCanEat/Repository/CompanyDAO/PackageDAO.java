package AllYouCanEat.Repository.CompanyDAO;

import AllYouCanEat.Entity.Company.Package;
import AllYouCanEat.Repository.Contract.CompanyRecord;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class PackageDAO implements CompanyRecord<Package> {

    private final DataSource dataSource;

    public PackageDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Package> retrieveValues() {
        List<Package> packages = new LinkedList<>();

        try (Connection connection = dataSource.getConnection()) {

            String sql = """
                    SELECT * FROM package
                    """;

            try (Statement statement = connection.createStatement()) {

                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    while (resultSet.next()) {

                        packages.add(new Package(
                                resultSet.getString("packageType"),
                                resultSet.getInt("price"),
                                resultSet.getInt("personCount")
                        ));

                    }

                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return packages;
    }
}
