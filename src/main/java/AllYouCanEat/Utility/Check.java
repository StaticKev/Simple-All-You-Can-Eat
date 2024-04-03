package AllYouCanEat.Utility;

import AllYouCanEat.Entity.Staff.TimeRecord;
import AllYouCanEat.Values.Shift;
import AllYouCanEat.Values.TableBlock;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

public class Check {

    private Check() {}

    public static boolean isWithin1(LocalDateTime time, Shift shift) {

        boolean isWithin = false;

        if (
                time.toLocalTime().isBefore(shift.getShiftEnd()) &&
                        time.toLocalTime().isAfter(shift.getShiftStart())
        ) {
            isWithin = true;
        }

        return isWithin;
    }

    public static boolean isWithin2(TimeRecord newRecord, TimeRecord presentRecord) {
        boolean isWithin = false;

        if (presentRecord.getBegin().isBefore(newRecord.getBegin()) && presentRecord.getBegin().plusHours(1).isAfter(newRecord.getBegin())) {
            isWithin = true;
        } else if (presentRecord.getBegin().isAfter(newRecord.getBegin())) {
            isWithin = true;
        } else if (presentRecord.getBegin().isEqual(newRecord.getBegin())) {
            isWithin = true;
        }

        return isWithin;
    }

    public static boolean blockCapacityIsEmpty(Map<TableBlock, Integer> blockCapacity, int personCount) {
        boolean isEmpty = true;

        for (Map.Entry<TableBlock, Integer> entry : blockCapacity.entrySet()) {

            if (entry.getValue() >= personCount) {
                isEmpty = false;
                break;
            }

        }

        return isEmpty;

    }

}