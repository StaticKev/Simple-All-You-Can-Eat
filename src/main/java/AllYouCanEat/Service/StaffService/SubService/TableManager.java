package AllYouCanEat.Service.StaffService.SubService;

import AllYouCanEat.Entity.Company.Table;
import AllYouCanEat.Entity.Helper.TableQueue;
import AllYouCanEat.Entity.Staff.Order;
import AllYouCanEat.Utility.Check;
import AllYouCanEat.Utility.InitVar;
import AllYouCanEat.Utility.MyFilter;
import AllYouCanEat.Values.TableBlock;

import java.util.*;

public class TableManager {

    public boolean assignReservation(List<Order> reservations, Order order, List<Table> tables) {

        HashSet<String> reservedTable = new HashSet<>(); // Occupied tables during that duration
        List<Table> availableTable = new ArrayList<>(); // Unoccupied tables during that duration
        Map<TableBlock, Integer> blockCapacity = new HashMap<>(); // The capacity of each block
        int restaurantCapacity = 0; // Restaurant capacity in that duration

        InitVar.initReservedTable(reservations, order, reservedTable); // Initializing variables

        // Assigning unreserved tables
        for (Table b : tables) {
            if (!reservedTable.contains(b.tableId())) {
                availableTable.add(b);
            }
        }

        // Initializing block capacity
        blockCapacity.put(TableBlock.BLOCK_A, 0);
        blockCapacity.put(TableBlock.BLOCK_B, 0);
        blockCapacity.put(TableBlock.BLOCK_C, 0);
        blockCapacity.put(TableBlock.BLOCK_D, 0);

        // Checking block capacity based on available table in that block
        for (Table d : availableTable) {
            if (d.tableBlock().equals(TableBlock.BLOCK_A)) {
                blockCapacity.replace(
                        TableBlock.BLOCK_A, blockCapacity.get(TableBlock.BLOCK_A) + d.availableSeat());
            } else if (d.tableBlock().equals(TableBlock.BLOCK_B)) {
                blockCapacity.replace(
                        TableBlock.BLOCK_B, blockCapacity.get(TableBlock.BLOCK_B) + d.availableSeat());
            } else if (d.tableBlock().equals(TableBlock.BLOCK_C)) {
                blockCapacity.replace(
                        TableBlock.BLOCK_C, blockCapacity.get(TableBlock.BLOCK_C) + d.availableSeat());
            } else {
                blockCapacity.replace(
                        TableBlock.BLOCK_D, blockCapacity.get(TableBlock.BLOCK_D) + d.availableSeat());
            }
        }

        // Assigning restaurant capacity
        for (var key : blockCapacity.keySet()) {
            restaurantCapacity = restaurantCapacity + blockCapacity.get(key);
        }

        // Checks if people could fit in the restaurant
        if (restaurantCapacity >= order.getPersonCount()) {

            if (Check.blockCapacityIsEmpty(blockCapacity, order.getPersonCount())) {
                // Jumlah orang tidak dapat dimuat dalam sebuah blok karena jumlah orang melebihi
                // kapasitas blok, maupun karena jumlah orang lebih kecil dari kapasitas blok namun
                // kursi yang tersedia pada tiap blok kurang dari jumlah orang.
                order.getTransaction()
                        .setTableOccupation(manageTable1(availableTable, order.getPersonCount()));

            } else {
                // Ini yang kapasitas block masih memenuhi dan jumlah orang lebih sedikit sama dengan
                // kapasitas blok.
                order.getTransaction()
                        .setTableOccupation(manageTable2(availableTable, blockCapacity, order.getPersonCount()));

            }
            return true;

        } else {
            // apologize after returning false
            return false;
        }

    }

    private List<String> manageTable1(List<Table> availableTable, Integer personCount) {
        List<String> result = new LinkedList<>();
        Map<Integer, Integer> tableWithCapacity = new HashMap<>();

        InitVar.initTableWithCapacity(tableWithCapacity, availableTable);
        boolean assignRemaining = false;

        List<Integer> reverseUpdatedCapacity = new ArrayList<>(tableWithCapacity.keySet());
        Collections.reverse(reverseUpdatedCapacity);

        List<Integer> removedTable = new ArrayList<>();

        while (personCount > 0) {

            if (assignRemaining) {

                int qualifiedCapacity = 0;

                if (!tableWithCapacity.isEmpty()) {
                    for (Map.Entry<Integer, Integer> entry : tableWithCapacity.entrySet()) {
                        qualifiedCapacity = qualifiedCapacity + (entry.getKey() * entry.getValue());
                    }
                }

                for (Integer capacityOf : reverseUpdatedCapacity) {

                    if (tableWithCapacity.get(capacityOf) > 0 && personCount - capacityOf >= 0) {
                        for (Table table : availableTable) {
                            if (table.availableSeat() == capacityOf) {
                                result.add(table.tableId());
                                availableTable.remove(table);
                                personCount = personCount - capacityOf;
                                tableWithCapacity.replace(capacityOf, tableWithCapacity.get(capacityOf) - 1);
                                break;
                            }
                        }
                        break;
                    }
                    else if (personCount < qualifiedCapacity && personCount - capacityOf < 0) {
                        int emptySeat = 10;
                        int smallestCapacity = 0;
                        for (Integer capacity : reverseUpdatedCapacity) {
                            if (tableWithCapacity.get(capacity) > 0 && capacity >= personCount) {
                                if (capacity - personCount < emptySeat) {
                                    emptySeat = capacity - personCount;
                                    smallestCapacity = capacity;
                                }
                            }
                        }

                        for (Table table : availableTable) {
                            if (table.availableSeat() == smallestCapacity) {
                                result.add(table.tableId());
                                availableTable.remove(table);
                                personCount = personCount - smallestCapacity;
                                tableWithCapacity.replace(smallestCapacity, tableWithCapacity.get(smallestCapacity) - 1);
                                break;
                            }
                        }
                        break;
                    }
                    else if (personCount > qualifiedCapacity) {
                        for (Table table : availableTable) {
                            if (table.availableSeat() == removedTable.get(0)) {
                                result.add(table.tableId());
                                availableTable.remove(table);
                                personCount = personCount - removedTable.get(0);
                                break;
                            }
                        }
                        break;
                    }

                }

            }
            else {

                for (Integer capacityOf : reverseUpdatedCapacity) {

                    if (tableWithCapacity.get(capacityOf) > 0 && personCount - capacityOf >= 0) {
                        for (Table table : availableTable) {
                            if (table.availableSeat() == capacityOf) {
                                result.add(table.tableId());
                                availableTable.remove(table);
                                personCount = personCount - capacityOf;
                                tableWithCapacity.replace(capacityOf, tableWithCapacity.get(capacityOf) - 1);
                                break;
                            }
                        }
                        break;
                    }
                    else if (tableWithCapacity.get(capacityOf) > 0 && personCount - capacityOf < 0) {
                        assignRemaining = true;
                        ArrayList<Integer> removeKey = new ArrayList<>();
                        for (Integer a : tableWithCapacity.keySet()) {
                            if (a > personCount || tableWithCapacity.get(a) == 0) {
                                removeKey.add(a);
                            }
                        }

                        for (Integer key : removeKey) {
                            if (tableWithCapacity.get(key) > 0) {
                                removedTable.add(key);
                            }
                            tableWithCapacity.remove(key);
                            reverseUpdatedCapacity.remove(key);
                        }
                        break;

                    }
                }
            }
        }

        return result;
    }

    private List<String> manageTable2(List<Table> availableTable, Map<TableBlock, Integer> blockCapacity, Integer personCount) {
        Map<TableBlock, TableQueue> tablesOnEachBlock = new HashMap<>();
        Map<TableBlock, Map<Integer, Integer>> blockUpdatedSeat = new HashMap<>();

        InitVar.initTablesOnEachBlock(tablesOnEachBlock, blockCapacity, personCount);
        InitVar.initBlockUpdatedSeat(blockUpdatedSeat, personCount, blockCapacity, availableTable);

        List<Table> occupiedTables = new ArrayList<>();
        for (Table table : availableTable) {
            if (!tablesOnEachBlock.containsKey(table.tableBlock())) {
                occupiedTables.add(table);
            }
        }

        for (Table table : occupiedTables) {
            availableTable.remove(table);
        }

        for (TableBlock tableBlock : tablesOnEachBlock.keySet()) {

            manage2(
                    personCount,
                    blockUpdatedSeat.get(tableBlock),
                    availableTable,
                    tablesOnEachBlock.get(tableBlock),
                    tableBlock
            );

        }

        // PRIORITAS BLOK!!
        int minSeat = 0;
        TableBlock returnResult = null;

        for (var result : tablesOnEachBlock.entrySet()) {
            if (minSeat == 0) {
                minSeat = result.getValue().getOccupiedSeat();
                returnResult = result.getKey();
            } else if (
                    (result.getKey().priorityLevel < returnResult.priorityLevel) ?
                            (result.getValue().getOccupiedSeat() == minSeat) : (result.getValue().getOccupiedSeat() < minSeat)

            ) {
                minSeat = result.getValue().getOccupiedSeat();
                returnResult = result.getKey();
            }

        }

        // Ini buat mbantu testing aja, nanti dihapus. (?)
        blockCapacity.replace(returnResult, blockCapacity.get(returnResult) - minSeat);

        // Hapus meja-meja dari blok yang akan dikembalikan dari `availableTable`
        tablesOnEachBlock.get(returnResult).getResult().forEach((tableId) -> {
            for (Table table : availableTable) {
                if (table.tableId().equals(tableId)) {
                    availableTable.remove(table);
                    break;
                }
            }
        });

        return tablesOnEachBlock.get(returnResult).getResult();
    }

    private void manage2(int personCount, Map<Integer, Integer> tableWithCapacity, List<Table> availableTable, TableQueue tableQueue, TableBlock tableBlock) {
        boolean assignRemaining = false;
        List<Table> tempTable = new ArrayList<>();

        MyFilter.filterByBlock(availableTable, tableBlock, tempTable);

        List<Integer> reverseUpdatedCapacity = new ArrayList<>(tableWithCapacity.keySet());
        Collections.reverse(reverseUpdatedCapacity);

        List<Integer> removedTable = new ArrayList<>();

        while (personCount > 0) {

            if (assignRemaining) {

                int qualifiedCapacity = 0;

                if (!tableWithCapacity.isEmpty()) {
                    for (Map.Entry<Integer, Integer> entry : tableWithCapacity.entrySet()) {
                        qualifiedCapacity = qualifiedCapacity + (entry.getKey() * entry.getValue());
                    }
                }

                for (Integer capacityOf : reverseUpdatedCapacity) {

                    if (tableWithCapacity.get(capacityOf) > 0 && personCount - capacityOf >= 0) {
                        for (Table table : tempTable) {
                            if (table.availableSeat() == capacityOf) {
                                tableQueue.getResult().add(table.tableId());
                                tableQueue.setOccupiedSeat(tableQueue.getOccupiedSeat() + table.availableSeat());
                                tempTable.remove(table);
                                personCount = personCount - capacityOf;
                                tableWithCapacity.replace(capacityOf, tableWithCapacity.get(capacityOf) - 1);
                                break;
                            }
                        }
                        break;
                    } else if (personCount < qualifiedCapacity && personCount - capacityOf < 0) {
                        int emptySeat = 10;
                        int smallestCapacity = 0;
                        for (Integer capacity : reverseUpdatedCapacity) {
                            if (tableWithCapacity.get(capacity) > 0 && capacity >= personCount) {
                                if (capacity - personCount < emptySeat) {
                                    emptySeat = capacity - personCount;
                                    smallestCapacity = capacity;
                                }
                            }
                        }

                        for (Table table : tempTable) {
                            if (table.availableSeat() == smallestCapacity) {
                                tableQueue.getResult().add(table.tableId());
                                tableQueue.setOccupiedSeat(tableQueue.getOccupiedSeat() + table.availableSeat());
                                tempTable.remove(table);
                                personCount = personCount - smallestCapacity;
                                tableWithCapacity.replace(smallestCapacity, tableWithCapacity.get(smallestCapacity) - 1);
                                break;
                            }
                        }
                        break;
                    } else if (personCount > qualifiedCapacity) {
                        for (Table table : tempTable) {
                            if (table.availableSeat() == removedTable.get(0) && table.tableBlock().equals(tableBlock)) {
                                tableQueue.getResult().add(table.tableId());
                                tableQueue.setOccupiedSeat(tableQueue.getOccupiedSeat() + table.availableSeat());
                                tempTable.remove(table);
                                personCount = personCount - removedTable.get(0);
                                break;
                            }
                        }
                        break;
                    }

                }

            } else {

                for (Integer capacityOf : reverseUpdatedCapacity) {
                    if (tableWithCapacity.get(capacityOf) > 0 && personCount - capacityOf >= 0) {
                        for (Table table : tempTable) {
                            if (table.availableSeat() == capacityOf) {
                                tableQueue.getResult().add(table.tableId());
                                tableQueue.setOccupiedSeat(tableQueue.getOccupiedSeat() + table.availableSeat());
                                tempTable.remove(table);
                                personCount = personCount - capacityOf;
                                tableWithCapacity.replace(capacityOf, tableWithCapacity.get(capacityOf) - 1);
                                break;
                            }
                        }
                        break;
                    } else if (tableWithCapacity.get(capacityOf) > 0 && personCount - capacityOf < 0) {
                        assignRemaining = true;
                        ArrayList<Integer> removeKey = new ArrayList<>();
                        for (Integer a : tableWithCapacity.keySet()) {
                            if (a > personCount || tableWithCapacity.get(a) == 0) {
                                removeKey.add(a);
                            }
                        }

                        for (Integer key : removeKey) {
                            if (tableWithCapacity.get(key) > 0) {
                                removedTable.add(key);
                            }
                            tableWithCapacity.remove(key);
                            reverseUpdatedCapacity.remove(key);
                        }
                        break;

                    }
                }
            }
        }

    }

}