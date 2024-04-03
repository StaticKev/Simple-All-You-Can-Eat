package AllYouCanEat.Repository;

import AllYouCanEat.Entity.Contract.Company;
import AllYouCanEat.Entity.Staff.Customer;
import AllYouCanEat.Entity.Staff.Order;
import AllYouCanEat.Repository.CompanyDAO.CashierDAO;
import AllYouCanEat.Repository.CompanyDAO.PackageDAO;
import AllYouCanEat.Repository.CompanyDAO.PaymentMethodDAO;
import AllYouCanEat.Repository.CompanyDAO.TableDAO;
import AllYouCanEat.Repository.Contract.CustomerDAO;
import AllYouCanEat.Repository.Contract.OrderRepo;
import AllYouCanEat.Repository.StaffDAO.*;
import AllYouCanEat.Values.FindBy;

import javax.sql.DataSource;
import java.util.List;

public class OrderRepoImpl implements OrderRepo {
    CashierDAO cashierDAO;
    PackageDAO packageDAO;
    PaymentMethodDAO paymentMethodDAO;
    TableDAO tableDAO;
    CustomerDAO customerDAO;
    OrderDAOImpl orderDAO;
    TimeRecordDAO timeRecordDAO;
    TransactionDAO transactionDAO;
    TransactionDetailDAO transactionDetailDAO;
    ViolationDAO violationDAO;
    TableOccupationDAO tableOccupationDAO;

    public OrderRepoImpl(DataSource dataSource) {
        cashierDAO = new CashierDAO(dataSource);
        packageDAO = new PackageDAO(dataSource);
        paymentMethodDAO = new PaymentMethodDAO(dataSource);
        tableDAO = new TableDAO(dataSource);

        customerDAO = new CustomerDAOImpl(dataSource);
        orderDAO = new OrderDAOImpl(dataSource);
        timeRecordDAO = new TimeRecordDAO(dataSource);
        transactionDAO = new TransactionDAO(dataSource);
        transactionDetailDAO = new TransactionDetailDAO(dataSource);
        violationDAO = new ViolationDAO(dataSource);
        tableOccupationDAO = new TableOccupationDAO(dataSource);

    }

    @Override
    public List<List<? extends Company>> retrieveCompanyRecord() {

        return List.of(
                cashierDAO.retrieveValues(),
                packageDAO.retrieveValues(),
                paymentMethodDAO.retrieveValues(),
                tableDAO.retrieveValues()
        );

    }

    @Override
    public List<Customer> findCustomer(Customer customer, FindBy findBy) {
        return customerDAO.retrieveCustomer(customer, findBy);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerDAO.updateDetail(customer);
    }

    @Override
    public int addCustomer(Customer customer) {
        return customerDAO.insertItem(customer);
    }

    @Override
    public boolean createOrder(Order order) {

        order.getTransaction().getTransactionDetail().setDetailId(
                transactionDetailDAO.insertItem(
                order.getTransaction().getTransactionDetail()
        ));

        order.getTransaction().setTransactionID(transactionDAO.insertItem(order.getTransaction()));
        order.getTimeRecord().setTimeRecordId(timeRecordDAO.insertItem(order.getTimeRecord()));
        order.getViolation().setViolationID(violationDAO.insertItem(order.getViolation()));

        order.setOrderId(orderDAO.insertItem(order));

        return tableOccupationDAO.insertItem(order.getOrderId(), order.getTransaction().getTableOccupation());

    }

}
