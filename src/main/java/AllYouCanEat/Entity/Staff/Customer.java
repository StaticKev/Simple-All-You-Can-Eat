package AllYouCanEat.Entity.Staff;

import AllYouCanEat.Entity.Contract.Staff;

public record Customer (
        Integer customerId, String name, String email, String phone

) implements Staff {}
