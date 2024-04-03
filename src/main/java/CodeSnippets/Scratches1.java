package CodeSnippets;

interface Interface1 {
    void methodA();
}

interface Interface2 extends Interface1 {
    void methodB();
}

class Interface1Impl implements Interface1 {

    public static void printMe(){}

    @Override
    public void methodA() {

    }
}

class Interface2Impl implements Interface2 {
    @Override
    public void methodA() {

    }

    @Override
    public void methodB() {

    }
    public class Canvas1 {

        public static void main(String[] args) {

            Interface1 obj1 = new Interface1Impl();
            Interface1 obj2 = new Interface2Impl();

//        Error karena Interface1Impl tidak implementasi Interface2
//        Interface2 obj3 = new Interface1Impl();
            Interface2 obj4 = new Interface2Impl();

            Interface1Impl.printMe(); // Static method tetap bisa digunakan

//        Object dengan tipe tertentu mewarisi semua method dan
//        variabel dari interfacenya.

            obj1.methodA();
            obj2.methodA();

//        Error karena walaupun obj2 mengimplementasikan Interface2
//        yang merupakan turunan dari Interface1. Tipe Interface1
//        membatasi objek untuk mengakses method dari turunannya,
//        Interface2.
//        obj2.methodB();

            obj4.methodA();
            obj4.methodB();

//        Idealnya 1 contract, untuk beberapa DAO (yang berkaitan).
//        Masing-masing DAO sebaiknya merujuk ke service layer yang
//        spesifik yang butuh DAO tersebut. Mungkin kontraknya bisa
//        dibuat generic? Jadi contract A bisa dibuat kontrak DAO
//        untuk masing-masing entity yang tipenya berbeda pada sebuah
//        service. Jumlah DAO idealnya tergantung jumlah tabel, 1
//        tabel 1 DAO.

        }
    }

}
