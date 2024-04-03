package CodeSnippets;

interface Test<T> {

    void printMe(T name);


}

class SubTest<T> implements Test<T> {

    @Override
    public void printMe(T name) {
        System.out.println(name);

    }
}

public class Scratches2 {

    public static void main (String[] args) { // Sketch

        String name = "Kevin";

        Test<Integer> test = new SubTest<>();
        Test<String> test2 = new SubTest<>();
        SubTest<Integer> test3 = new SubTest<>();
//
//        SubTest<String> test4 = test;

        test2.printMe(name);
        test3.printMe(2);


    }
}