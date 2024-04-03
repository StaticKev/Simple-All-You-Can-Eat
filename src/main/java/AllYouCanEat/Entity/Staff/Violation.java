package AllYouCanEat.Entity.Staff;

import AllYouCanEat.Entity.Contract.Staff;

import java.time.Duration;

public class Violation implements Staff {

    private int violationID;
    private boolean typeA; // Leftover
    private int weight; // Gram
    private boolean typeB; // Time exceeded
    private long duration; // In minute
    private boolean typeC; // Equipment break
    private int amount;

    public int getViolationID() {
        return violationID;
    }

    public void setViolationID(int violationID) {
        this.violationID = violationID;
    }

    public boolean isTypeA() {
        return typeA;
    }

    public void setTypeA(boolean typeA) {
        this.typeA = typeA;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isTypeB() {
        return typeB;
    }

    public void setTypeB(boolean typeB) {
        this.typeB = typeB;
    }

    public long getDurationV() {
        return duration;
    }

    public void setDurationV(long duration) {
        this.duration = duration;
    }

    public boolean isTypeC() {
        return typeC;
    }

    public void setTypeC(boolean typeC) {
        this.typeC = typeC;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
