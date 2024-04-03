package AllYouCanEat.Utility;

import AllYouCanEat.Entity.Company.Table;
import AllYouCanEat.Values.TableBlock;

import java.util.List;

public class MyFilter {

    private MyFilter() {}

    public static void filterByBlock(List<Table> availableTable, TableBlock tableBlock, List<Table> tempTable) {

        for (Table table : availableTable) {
            if (table.tableBlock().equals(tableBlock)) {
                tempTable.add(table);
            }
        }

    }

}
