package AllYouCanEat.Utility;

public class IdGenerator {

    private IdGenerator() {};

    public static String generateCsId(int lastId) {

        String formatter = "%07d";

        return "CS" + String.format(formatter, lastId);

    }

}
