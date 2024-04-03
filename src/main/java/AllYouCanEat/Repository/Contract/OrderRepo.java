package AllYouCanEat.Repository.Contract;

import AllYouCanEat.Entity.Contract.Company;
import AllYouCanEat.Entity.Staff.Customer;
import AllYouCanEat.Entity.Staff.Order;
import AllYouCanEat.Values.FindBy;

import java.util.List;

public interface OrderRepo {

    List<List<? extends Company>> retrieveCompanyRecord();

    List<Customer> findCustomer(Customer customer, FindBy findBy);
    // Mencari dan mengembalikan Customer (return Customer)
    // Nanti ini dipakai dalam method pada service. Kalau tidak ditemukan, kembalikan null,
    // kemudian panggil method pada repository untuk menambahkan item.
    // .retrieveCustomer()

    void updateCustomer(Customer customer);
    // Mengupdate Customer (void)
    // Jika Customer ditemukan dan dikehendaki perubahan pada record, method ini dipanggil.
    // .updateDetail()

    int addCustomer(Customer customer);
    // Menambahkan Customer (return int)
    // .insertItem() .retrieveLastID()

    boolean createOrder(Order order);

}
