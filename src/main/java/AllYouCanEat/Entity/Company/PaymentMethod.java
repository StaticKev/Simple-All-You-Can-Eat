package AllYouCanEat.Entity.Company;

import AllYouCanEat.Entity.Contract.Company;
import AllYouCanEat.Values.AvailablePaymentMethod;

public record PaymentMethod (
        int pmID,
        Merchant merchant,
        AvailablePaymentMethod availablePaymentMethod

) implements Company {}
