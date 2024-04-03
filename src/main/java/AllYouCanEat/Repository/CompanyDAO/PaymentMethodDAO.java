package AllYouCanEat.Repository.CompanyDAO;

import AllYouCanEat.Entity.Company.Merchant;
import AllYouCanEat.Entity.Company.PaymentMethod;
import AllYouCanEat.Repository.Contract.CompanyRecord;
import AllYouCanEat.Values.AvailablePaymentMethod;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class PaymentMethodDAO implements CompanyRecord<PaymentMethod> {

    private final DataSource dataSource;

    public PaymentMethodDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<PaymentMethod> retrieveValues() {
        List<PaymentMethod> paymentMethods = new LinkedList<>();

        try (Connection connection = dataSource.getConnection()) {

            String sql = """
                    SELECT pt.pmID, pt.available_pm, pt.merchantID, mt.merchantName, mt.discountValue
                    FROM payment_method AS pt
                    JOIN merchant AS mt
                    on (pt.merchantID = mt.merchantID);
                    """;

            try (Statement statement = connection.createStatement()) {

                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    while (resultSet.next()) {

                        paymentMethods.add(new PaymentMethod(
                                resultSet.getInt("pmID"),
                                new Merchant(resultSet.getString("merchantName"), resultSet.getFloat("discountValue")),
                                AvailablePaymentMethod.valueOf(resultSet.getString("available_pm"))
                                ));

                    }

                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return paymentMethods;
    }
}
