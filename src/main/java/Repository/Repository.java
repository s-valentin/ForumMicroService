package Repository;

import Model.Entity;
import Model.ForumList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public interface Repository<E extends Entity> {

    //principiile CRUD.

    public E findOne(int id); // read

    public List<E> findAll(); // read

    public boolean save(E entity); // create

    public boolean update(E entity); // update

    public boolean delete(int id); // delete


    // * Face conexiunea cu baza de date si o returneaza.
    public static Connection getConnection() {

        String url = System.getProperty("spring.datasource.url");
        String username = System.getProperty("spring.datasource.username");
        String password = System.getProperty("spring.datasource.password");

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }

}
