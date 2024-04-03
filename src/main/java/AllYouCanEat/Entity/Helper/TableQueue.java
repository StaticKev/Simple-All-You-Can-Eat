package AllYouCanEat.Entity.Helper;

import java.util.ArrayList;
import java.util.List;

public class TableQueue {

    public TableQueue() {
        result = new ArrayList<>();
    }
    List<String> result;
    int occupiedSeat;

    public List<String> getResult() {
        return result;
    }

    public int getOccupiedSeat() {
        return occupiedSeat;
    }

    public void setOccupiedSeat(int occupiedSeat) {
        this.occupiedSeat = occupiedSeat;
    }
}
