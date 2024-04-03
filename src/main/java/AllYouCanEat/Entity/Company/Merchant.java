package AllYouCanEat.Entity.Company;

import AllYouCanEat.Entity.Contract.Company;

public record Merchant (
        String merchantName,
        float discountValue
) implements Company {}
