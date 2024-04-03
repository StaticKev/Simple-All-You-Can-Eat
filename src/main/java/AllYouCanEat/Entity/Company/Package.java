package AllYouCanEat.Entity.Company;

import AllYouCanEat.Entity.Contract.Company;

public record Package (
        String packageType,
        int price,
        int personCount
) implements Company {}
