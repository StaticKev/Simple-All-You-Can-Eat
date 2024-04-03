package AllYouCanEat.Entity.Company;

import AllYouCanEat.Entity.Contract.Company;
import AllYouCanEat.Values.TableBlock;

public record Table (
        String tableId,
        int availableSeat,
        TableBlock tableBlock
) implements Company {}
