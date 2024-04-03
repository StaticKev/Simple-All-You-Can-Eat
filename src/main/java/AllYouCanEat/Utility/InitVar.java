package AllYouCanEat.Utility;

import AllYouCanEat.Entity.Company.Table;
import AllYouCanEat.Entity.Helper.TableQueue;
import AllYouCanEat.Entity.Staff.Order;
import AllYouCanEat.Values.TableBlock;

import java.util.*;

public class InitVar {

    private InitVar() {};

    public static void initTable(List<Table> tableList) {

        TableBlock tableBlock = null;
        String strf = "T%02d";
        int id = 1;

        for (var a = 1; a <= 4; a++) {

            switch (a) {
                case 1 -> tableBlock = TableBlock.BLOCK_A;
                case 2 -> tableBlock = TableBlock.BLOCK_B;
                case 3 -> tableBlock = TableBlock.BLOCK_C;
                case 4 -> tableBlock = TableBlock.BLOCK_D;
            }

            for (int b = 1; b <= 9; b++) {
                if (b <= 2) {
                    tableList.add(new Table(String.format(strf, id), 1, tableBlock));
                } else if (b <= 4) {
                    tableList.add(new Table(String.format(strf, id), 2, tableBlock));
                } else if (b <= 6) {
                    tableList.add(new Table(String.format(strf, id), 4, tableBlock));
                } else if (b <= 8) {
                    tableList.add(new Table(String.format(strf, id), 6, tableBlock));
                } else {
                    tableList.add(new Table(String.format(strf,id), 10, tableBlock));
                }
                id++;
            }
        }

    }

    public static void initReservedTable(List<Order> reservations, Order order, HashSet<String> reservedTable) {

        if (!reservations.isEmpty()) {
            for (var a : reservations) {
                if (Check.isWithin2(order.getTimeRecord(), a.getTimeRecord())) {
                    reservedTable.addAll(a.getTransaction().getTableOccupation());
                }
            }
        }

    }

    public static void initTablesOnEachBlock(Map<TableBlock, TableQueue> tablesOnEachBlock, Map<TableBlock, Integer> blockCapacity, Integer personCount) {
        blockCapacity.forEach((key, value) -> {
            if (value >= personCount) {
                tablesOnEachBlock.put(key, new TableQueue());
            }
        });
    }

    public static void initBlockUpdatedSeat(Map<TableBlock, Map<Integer, Integer>> blockUpdatedSeat, int personCount, Map<TableBlock, Integer> blockCapacity, List<Table> availableTable) {
        for (var capacityOf : blockCapacity.entrySet()) {

            if (capacityOf.getValue() >= personCount) {
                blockUpdatedSeat.put(capacityOf.getKey(), new HashMap<>());
                blockUpdatedSeat.get(capacityOf.getKey()).put(1, 0);
                blockUpdatedSeat.get(capacityOf.getKey()).put(2, 0);
                blockUpdatedSeat.get(capacityOf.getKey()).put(4, 0);
                blockUpdatedSeat.get(capacityOf.getKey()).put(6, 0);
                blockUpdatedSeat.get(capacityOf.getKey()).put(10, 0);

                for (var table : availableTable) {
                    if (table.tableBlock().equals(capacityOf.getKey())) {
                        switch (table.availableSeat()) {
                            case 1 -> blockUpdatedSeat.get(capacityOf.getKey())
                                    .replace(1, blockUpdatedSeat.get(capacityOf.getKey()).get(1) + 1);
                            case 2 -> blockUpdatedSeat.get(capacityOf.getKey())
                                    .replace(2, blockUpdatedSeat.get(capacityOf.getKey()).get(2) + 1);
                            case 4 -> blockUpdatedSeat.get(capacityOf.getKey())
                                    .replace(4, blockUpdatedSeat.get(capacityOf.getKey()).get(4) + 1);
                            case 6 -> blockUpdatedSeat.get(capacityOf.getKey())
                                    .replace(6, blockUpdatedSeat.get(capacityOf.getKey()).get(6) + 1);
                            case 10 -> blockUpdatedSeat.get(capacityOf.getKey())
                                    .replace(10, blockUpdatedSeat.get(capacityOf.getKey()).get(10) + 1);
                        }
                    }
                }

            }

        }
    }

    public static void initTableWithCapacity(Map<Integer, Integer> tableWithCapacity, List<Table> availableTable) {

        for (var table : availableTable) {

            if (!tableWithCapacity.containsKey(table.availableSeat())) {
                tableWithCapacity.put(table.availableSeat(), 0);
            }

        }

        for (var table : availableTable) {

            if (tableWithCapacity.containsKey(table.availableSeat())) {
                tableWithCapacity.replace(table.availableSeat(), tableWithCapacity.get(table.availableSeat()) + 1);
            }

        }

    }

}
