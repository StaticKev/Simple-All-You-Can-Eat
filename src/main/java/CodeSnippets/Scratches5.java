package CodeSnippets;

import AllYouCanEat.Entity.Company.Merchant;
import AllYouCanEat.Entity.Company.PaymentMethod;
import AllYouCanEat.Entity.Company.Table;
import AllYouCanEat.Entity.Staff.Customer;
import AllYouCanEat.Repository.Contract.OrderRepo;
import AllYouCanEat.Repository.OrderRepoImpl;
import AllYouCanEat.Service.Contract.OrderManager;
import AllYouCanEat.Service.StaffService.OrderManagerImpl;
import AllYouCanEat.Utility.InitVar;
import AllYouCanEat.Utility.MyConnection;
import AllYouCanEat.Values.AvailablePaymentMethod;
import AllYouCanEat.View.MainView;
import com.zaxxer.hikari.HikariDataSource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scratches5 {

    public static void main(String[] args) {

        HikariDataSource dataSource = MyConnection.getDataSource();

        OrderRepo orderRepo = new OrderRepoImpl(dataSource);
        OrderManager orderManager = new OrderManagerImpl(orderRepo);

        Customer customer = new Customer(
                1, "Kamisato Ayato", "ayatoluvboba@inazuma.com", "1235263295635");
//        List<Integer> integers = List.of(34, 23, 54, 33);

//        int a = 0;

        // 33 bisa tapi 34 udah ndak cukup
        // Resto dipenuhin
        // Ntar coba untuk kurun waktu yang beda
//        for (Integer i : integers) {
//            a = orderManager.newOrder(customer, i, LocalDateTime.now());
//            System.out.println(a);
//            System.out.println(orderManager.getOrder(a).getTransaction().getTableOccupation());
//        }

        // Setelah penuh di kurun waktu tertentu, isi lagi di kurun waktu lain
        int b = orderManager.newOrder(customer, 85, LocalDateTime.now().plusMinutes(80));
        System.out.println(b);
        System.out.println(orderManager.getOrder(b).getTransaction().getTableOccupation());

        assert b != 0;
        orderManager.finishOrder(b, LocalDateTime.now().plusMinutes(50));

    }

}
