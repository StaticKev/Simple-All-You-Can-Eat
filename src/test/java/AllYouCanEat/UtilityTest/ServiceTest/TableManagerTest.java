package AllYouCanEat.UtilityTest.ServiceTest;

import AllYouCanEat.Entity.Company.Table;
import AllYouCanEat.Entity.Staff.Customer;
import AllYouCanEat.Entity.Staff.Order;
import AllYouCanEat.Repository.CompanyDAO.TableDAO;
import AllYouCanEat.Service.StaffService.SubService.TableManager;
import AllYouCanEat.Utility.MyConnection;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TableManagerTest {

    @Test
    void assignReservationTest() {

        DataSource dataSource = MyConnection.getDataSource();

        TableDAO tableDAO = new TableDAO(dataSource);
        List<Table> tables = tableDAO.retrieveValues();

        for (int a = 0; a < 36; a++) {
            if (a % 9 == 0) {
                System.out.println();
            }
            System.out.print(tables.get(a).tableId() + " (" + tables.get(a).availableSeat() + ") ");
        }

        TableManager tm = new TableManager();

        Customer customer = new Customer(10, "asdf", "asf", "asd");

        List<Order> reservations = new ArrayList<>();

        Order order = new Order(
                new Customer(10, "asdf", "asf", "asd"),
                30,
                LocalDateTime.now()
        );

        tm.assignReservation(reservations, order, tables);

        System.out.println(reservations.size());
        System.out.println(order.getTransaction().getTableOccupation().toString());


    }

}
