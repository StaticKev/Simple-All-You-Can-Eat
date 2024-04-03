package AllYouCanEat.Values;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;

public enum Shift {

    MORNING_SHIFT(LocalTime.of(8, 0), LocalTime.of(1, 0)),
    AFTERNOON_SHIFT(LocalTime.of(1, 0), LocalTime.of(6, 0)),
    NIGHT_SHIFT(LocalTime.of(6, 0), LocalTime.of(11, 0));

    private final LocalTime shiftStart;
    private final LocalTime shiftEnd;

    Shift(LocalTime shiftStart, LocalTime shiftEnd) {
        this.shiftStart = shiftStart;
        this.shiftEnd = shiftEnd;

    }

    public LocalTime getShiftStart() {
        return shiftStart;
    }

    public LocalTime getShiftEnd() {
        return shiftEnd;
    }

}
