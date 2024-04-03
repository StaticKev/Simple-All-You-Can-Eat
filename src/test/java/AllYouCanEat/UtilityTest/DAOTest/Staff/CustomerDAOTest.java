package AllYouCanEat.UtilityTest.DAOTest.Staff;

import AllYouCanEat.Entity.Staff.Customer;
import AllYouCanEat.Repository.Contract.CustomerDAO;
import AllYouCanEat.Repository.StaffDAO.CustomerDAOImpl;
import AllYouCanEat.Utility.MyConnection;
import AllYouCanEat.Values.FindBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.List;

public class CustomerDAOTest {

    @Test
    void updateTest() {

        DataSource dataSource = MyConnection.getDataSource();

        CustomerDAO customerDAO = new CustomerDAOImpl(dataSource);

        customerDAO.updateDetail(new Customer(
                1, "Kamisato Ayato", "ayatoluvboba@inazuma.com", "1235263295635"
                ));

    }

    @Test
    void retrieveCustomerTest() {

        DataSource dataSource = MyConnection.getDataSource();

        CustomerDAO customerDAO = new CustomerDAOImpl(dataSource);

        List<Customer> customers = customerDAO.retrieveCustomer(
                new Customer(0, "k", "k", "235262"),
                FindBy.NAME);

        for (Customer c : customers) {
            System.out.println(
                    "ID = " + c.customerId() +
                    "\nName = " + c.name() +
                    "\nEmail = " + c.email() +
                    "\nPhone = " + c.phone()
            );
        }

    }

    @Test
    void insertCustomerTest() {

        DataSource dataSource = MyConnection.getDataSource();

        CustomerDAO customerDAO = new CustomerDAOImpl(dataSource);

        customerDAO.insertItem(
                new Customer(null, "Yae Miko", "righthererightnow@inazuma.com", "-")
        );

    }

}
