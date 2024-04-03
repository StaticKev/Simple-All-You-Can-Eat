package AllYouCanEat.Entity.Staff;

import AllYouCanEat.Entity.Contract.Staff;

import java.time.LocalDateTime;

public class TimeRecord implements Staff {

    private int timeRecordId;
    private final LocalDateTime begin;
    private LocalDateTime end;

    public TimeRecord(LocalDateTime begin) {
        this.begin = begin;
    }

    public LocalDateTime getBegin() {
        return begin;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public int getTimeRecordId() {
        return timeRecordId;
    }

    public void setTimeRecordId(int timeRecordId) {
        this.timeRecordId = timeRecordId;
    }
}