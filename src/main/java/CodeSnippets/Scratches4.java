package CodeSnippets;

import AllYouCanEat.Repository.Contract.OrderRepo;
import AllYouCanEat.Repository.OrderRepoImpl;
import AllYouCanEat.Service.Contract.OrderManager;
import AllYouCanEat.Service.StaffService.OrderManagerImpl;
import AllYouCanEat.Utility.MyConnection;
import AllYouCanEat.View.MainView;
import com.zaxxer.hikari.HikariDataSource;

public class Scratches4 {

    public static void main(String[] args) {

        HikariDataSource dataSource = MyConnection.getDataSource();

        OrderRepo orderRepo = new OrderRepoImpl(dataSource);
        OrderManager orderManager = new OrderManagerImpl(orderRepo);
        MainView mainView = new MainView(orderManager);

        mainView.homeView();

    }
}
