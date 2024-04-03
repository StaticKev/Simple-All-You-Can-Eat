package AllYouCanEat.Utility;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class MyConnection {

    private static final HikariDataSource dataSource;

    private MyConnection() {};

    static {
        HikariConfig config = new HikariConfig();

        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/all_you_can_eat_copy");
        config.setUsername("root");
        config.setPassword("#Staticuser646");
        config.setMaximumPoolSize(5);
        config.setMinimumIdle(5);
        config.setIdleTimeout(6000);
        config.setMaxLifetime(60000);

        dataSource = new HikariDataSource(config);

    }

    public static HikariDataSource getDataSource() {
        return dataSource;
    }

}
