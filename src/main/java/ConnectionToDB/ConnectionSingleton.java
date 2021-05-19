package ConnectionToDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {

    private static Connection connection;

    private ConnectionSingleton() {
    }

    // * Face conexiunea cu baza de date si o returneaza.
    public static Connection getConnection() {

        String url = System.getProperty("spring.datasource.url");
        String username = System.getProperty("spring.datasource.username");
        String password = System.getProperty("spring.datasource.password");


        try {
            if (connection == null)
                connection = DriverManager.getConnection(url, username, password);
            else if(connection.isClosed())
                connection = DriverManager.getConnection(url, username, password);
            else if(!connection.isValid(10))
                connection = DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
