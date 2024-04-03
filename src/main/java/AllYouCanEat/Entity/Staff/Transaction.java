package AllYouCanEat.Entity.Staff;

import AllYouCanEat.Entity.Company.PaymentMethod;
import AllYouCanEat.Entity.Company.Cashier;
import AllYouCanEat.Entity.Contract.Staff;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Transaction implements Staff {

    private int transactionID;
    private List<String> tableOccupation;
    private LocalDateTime transactionTime;
    private final TransactionDetail transactionDetail;
    private PaymentMethod paymentMethod;
    private Cashier cashier;

    public Transaction() {
        tableOccupation = new ArrayList<>();
        transactionDetail = new TransactionDetail();
    }

    public List<String> getTableOccupation() {
        return tableOccupation;
    }

    public void setTableOccupation(List<String> tableOccupation) {
        this.tableOccupation = tableOccupation;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public TransactionDetail getTransactionDetail() {
        return transactionDetail;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }
}
