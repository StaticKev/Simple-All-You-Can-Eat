package AllYouCanEat.Entity.Staff;

import AllYouCanEat.Entity.Company.Package;
import AllYouCanEat.Entity.Contract.Staff;

public class TransactionDetail implements Staff {

    private int tDetailId;
    private Package packageType;
    private int initialPrice;
    private int violationCharge;
    private int tax;
    private int discount;
    private int total;
    private int payment;
    private int change;

    public Package getPackageType() {
        return packageType;
    }

    public void setPackageType(Package packageType) {
        this.packageType = packageType;
    }

    public int getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(int initialPrice) {
        this.initialPrice = initialPrice;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public int getChange() {
        return change;
    }

    public void setChange(int change) {
        this.change = change;
    }

    public int gettDetailId() {
        return tDetailId;
    }

    public void setDetailId(int tDetailId) {
        this.tDetailId = tDetailId;
    }

    public int getViolationCharge() {
        return violationCharge;
    }

    public void setViolationCharge(int violationCharge) {
        this.violationCharge = violationCharge;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int disount) {
        this.discount = disount;
    }
}
