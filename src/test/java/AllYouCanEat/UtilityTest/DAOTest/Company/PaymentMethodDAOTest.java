package AllYouCanEat.UtilityTest.DAOTest.Company;

import AllYouCanEat.Entity.Company.PaymentMethod;
import AllYouCanEat.Repository.CompanyDAO.PaymentMethodDAO;
import AllYouCanEat.Utility.MyConnection;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.util.List;

public class PaymentMethodDAOTest {

    @Test
    void paymentMethodDAOTest() {

        DataSource dataSource = MyConnection.getDataSource();

        PaymentMethodDAO paymentMethodDAO = new PaymentMethodDAO(dataSource);

        List<PaymentMethod> paymentMethods = paymentMethodDAO.retrieveValues();

        for(PaymentMethod pm : paymentMethods) {
            System.out.println(
                    "pmID = " + pm.pmID() + "\nPayment Method = " + pm.availablePaymentMethod() +
                            "\nMerchant Name = " + pm.merchant().merchantName() +
                            "\nDiscount Value = " + pm.merchant().discountValue() +
                            "\n-------------------------------------------"
            );
        }

    }

}
