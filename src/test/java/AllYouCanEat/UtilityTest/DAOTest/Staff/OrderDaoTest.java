package AllYouCanEat.UtilityTest.DAOTest.Staff;

import AllYouCanEat.Entity.Staff.Customer;
import AllYouCanEat.Entity.Staff.Order;
import AllYouCanEat.Repository.Contract.OrderDAO;
import AllYouCanEat.Repository.StaffDAO.OrderDAOImpl;
import AllYouCanEat.Utility.MyConnection;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.time.LocalDateTime;

public class OrderDaoTest {

    @Test
    void insertionTest() {

        DataSource dataSource = MyConnection.getDataSource();

        OrderDAO<Order> orderDAO = new OrderDAOImpl(dataSource);

        Order order = new Order(
                new Customer(1, "asfa", "asf", "afs"),
                120,
                LocalDateTime.now()
        );

        order.getTimeRecord().setTimeRecordId(1);
        order.getViolation().setViolationID(1);
        order.getTransaction().setTransactionID(3);

        System.out.println(orderDAO.insertItem(order));

    }

}
