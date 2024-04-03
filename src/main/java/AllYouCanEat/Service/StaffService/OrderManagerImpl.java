package AllYouCanEat.Service.StaffService;

import AllYouCanEat.Entity.Company.PaymentMethod;
import AllYouCanEat.Entity.Company.Table;
import AllYouCanEat.Entity.Contract.Company;
import AllYouCanEat.Entity.Staff.Customer;
import AllYouCanEat.Entity.Staff.Order;
import AllYouCanEat.Entity.Company.Package;
import AllYouCanEat.Repository.Contract.OrderRepo;
import AllYouCanEat.Service.Contract.OrderManager;
import AllYouCanEat.Service.StaffService.SubService.TableManager;
import AllYouCanEat.Entity.Company.Cashier;
import AllYouCanEat.Service.StaffService.SubService.ViolationChecker;
import AllYouCanEat.Utility.Check;
import AllYouCanEat.Values.Shift;
import AllYouCanEat.View.TempView.FinishOrderView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderManagerImpl implements OrderManager {

    private final OrderRepo repo;
    private final TableManager tm; // Sub-service
    private final ViolationChecker vc; // Sub-service
    private final List<Table> tables;
    private final List<Cashier> cashiers;
    private final List<PaymentMethod> paymentMethods;
    private final List<Package> packages;
    List<Order> orders;

    public OrderManagerImpl(OrderRepo repo) {
        tm = new TableManager();
        vc = new ViolationChecker();
        cashiers = new ArrayList<>();
        tables = new ArrayList<>();
        orders = new ArrayList<>();
        paymentMethods = new ArrayList<>();
        packages = new ArrayList<>();
        this.repo = repo;

        List<List<? extends Company>> companyRecord = repo.retrieveCompanyRecord();

        for (List<? extends Company> list : companyRecord) {

            if (!list.isEmpty() && list.get(0) instanceof Cashier) {
                for (Company c : list) {
                    cashiers.add((Cashier) c);
                }
            } else if (!list.isEmpty() && list.get(0) instanceof Table) {
                for (Company c : list) {
                    tables.add((Table) c);
                }
            } else if (!list.isEmpty() && list.get(0) instanceof PaymentMethod) {
                for (Company c : list) {
                    paymentMethods.add((PaymentMethod) c);
                }
            } else if (!list.isEmpty() && list.get(0) instanceof Package) {
                for (Company c : list) {
                    packages.add((Package) c);
                }
            }

        }

    }

    @Override
    public int newOrder(Customer customer, int personCount, LocalDateTime begin) {

        Order order = new Order(customer, personCount, begin);
        int isSuccess = 0;

        order.setTempOrderId(orders.size() + 1);

        if (tm.assignReservation(orders, order, tables)) {
            int initialPrice = 0;
            int singlePrice = Integer.MAX_VALUE;
            Package tempPkg = null;

            for (Package pkg1 : packages) {
                if (pkg1.price() < singlePrice) {
                    singlePrice = pkg1.price();
                }
            }

            for (Package pkg2 : packages) {
                if (personCount >= pkg2.personCount()) {
                    tempPkg = pkg2;
                    initialPrice =
                            (personCount / pkg2.personCount() * pkg2.price()) +
                                    (personCount % pkg2.personCount() * singlePrice);
                    break;
                }
            }

            order.getTransaction().getTransactionDetail().setInitialPrice(initialPrice);
            order.getTransaction().getTransactionDetail().setPackageType(tempPkg);

            orders.add(order);
            isSuccess = order.getTempOrderId();

        } else {
            System.out.println("No enough space available");
        }

        // Ngembalikan tempOrderId
        return isSuccess;

    }

    @Override
    public boolean finishOrder(int tempOrderId, LocalDateTime end) {
        Order order = null;
        PaymentMethod paymentMethod = FinishOrderView.getPm(paymentMethods);

        for (Order o : orders) {
            if (o.getTempOrderId() == tempOrderId) {
                order = o;
            }
        }

        assert order != null;

        Shift currentShift;

        if (Check.isWithin1(LocalDateTime.now(), Shift.MORNING_SHIFT)) {
            currentShift = Shift.MORNING_SHIFT;
        } else if (Check.isWithin1(LocalDateTime.now(), Shift.AFTERNOON_SHIFT)) {
            currentShift = Shift.AFTERNOON_SHIFT;
        } else {
            currentShift = Shift.NIGHT_SHIFT;
        }

        for (Cashier cashierA : cashiers) {
            if (cashierA.shift().equals(currentShift)) {
                order.getTransaction().setCashier(cashierA);
                break;
            }
        }

        order.getTransaction().setPaymentMethod(paymentMethod);
        order.getTimeRecord().setEnd(end);
        order.getTransaction().setTransactionTime(LocalDateTime.now());

        vc.violationCheck(order.getViolation(), order.getTimeRecord());

        order.getTransaction().getTransactionDetail().setViolationCharge(
                (50_000 * order.getViolation().getWeight() / 100) +
                        (100_000 * (int)order.getViolation().getDurationV() / 30) +
                        (200_000 * order.getViolation().getAmount())
        );
        order.getTransaction().getTransactionDetail().setTax(
                Math.round(0.12f * order.getTransaction().getTransactionDetail().getInitialPrice())
        );
        order.getTransaction().getTransactionDetail().setDiscount(
                Math.round(
                        order.getTransaction().getTransactionDetail().getInitialPrice() *
                                order.getTransaction().getPaymentMethod().merchant().discountValue()
                )
        );

        order.getTransaction().getTransactionDetail().setTotal(
                order.getTransaction().getTransactionDetail().getInitialPrice() +
                        order.getTransaction().getTransactionDetail().getViolationCharge() +
                        order.getTransaction().getTransactionDetail().getTax() -
                        order.getTransaction().getTransactionDetail().getDiscount()

        );

        // Method buat ngambil payment
        order.getTransaction().getTransactionDetail().setPayment(
                FinishOrderView.obtainPayment()
        );

        order.getTransaction().getTransactionDetail().setChange(
                order.getTransaction().getTransactionDetail().getPayment() -
                        order.getTransaction().getTransactionDetail().getTotal()
        );

        return repo.createOrder(order);

    }

    @Override
    public Order getOrder(int i) {
        return orders.get(i - 1);
    }

}