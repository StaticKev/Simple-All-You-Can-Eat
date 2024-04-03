package AllYouCanEat.Repository.Contract;

import AllYouCanEat.Entity.Staff.Customer;
import AllYouCanEat.Values.FindBy;

import java.util.List;

public interface CustomerDAO extends OrderDAO<Customer> {

    void updateDetail(Customer item);

    List<Customer> retrieveCustomer(Customer tempCustomer, FindBy findBy);

}
