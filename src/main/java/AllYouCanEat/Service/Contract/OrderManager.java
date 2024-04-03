package AllYouCanEat.Service.Contract;

import AllYouCanEat.Entity.Company.Package;
import AllYouCanEat.Entity.Company.PaymentMethod;
import AllYouCanEat.Entity.Staff.Customer;
import AllYouCanEat.Entity.Staff.Order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface OrderManager {

    int newOrder(Customer customer, int personCount, LocalDateTime begin);
    /*
    assignCustomer
     */

    boolean finishOrder(int tempOrderid, LocalDateTime localDateTime);

    Order getOrder(int i);

}
