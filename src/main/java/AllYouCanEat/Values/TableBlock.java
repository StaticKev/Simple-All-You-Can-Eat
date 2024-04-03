package AllYouCanEat.Values;

public enum TableBlock {

    BLOCK_A(1),
    BLOCK_B(2),
    BLOCK_C(3),
    BLOCK_D(4);
    public final int priorityLevel;

    TableBlock(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

}
/*

   2:
   4:
   6:
   10:

 */