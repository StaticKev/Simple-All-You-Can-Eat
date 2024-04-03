package AllYouCanEat.Entity.Company;

import AllYouCanEat.Entity.Contract.Company;
import AllYouCanEat.Values.Shift;

public record Cashier (
        int cashierId,
        String cashierName,
        Shift shift
) implements Company {}
