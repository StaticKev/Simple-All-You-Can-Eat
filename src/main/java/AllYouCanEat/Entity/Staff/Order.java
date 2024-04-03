package AllYouCanEat.Entity.Staff;

import AllYouCanEat.Entity.Contract.Staff;

import java.time.LocalDateTime;

public class Order implements Staff {

    private final Customer customer;
    private int orderId;
    private int tempOrderId;
    private final int personCount;
    private final TimeRecord timeRecord;
    private final Transaction transaction;
    private final Violation violation;

    public Order(Customer customer, int personCount, LocalDateTime begin) {
        timeRecord = new TimeRecord(begin);
        transaction = new Transaction();
        violation = new Violation();

        this.customer = customer;
        this.personCount = personCount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getPersonCount() {
        return personCount;
    }

    public TimeRecord getTimeRecord() {
        return timeRecord;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public Violation getViolation() {
        return violation;
    }

    public int getTempOrderId() {
        return tempOrderId;
    }

    public void setTempOrderId(int tempOrderId) {
        this.tempOrderId = tempOrderId;
    }
}
