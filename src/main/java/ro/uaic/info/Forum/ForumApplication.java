package ro.uaic.info.Forum;

import ConnectionToDB.ConnectionSingleton;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;


@SpringBootApplication
//@EnableEurekaClient
public class ForumApplication {

    public static void main(String[] args) {

        try {
            System.getProperties().load(new FileReader("src/main/resources/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Connection connection = ConnectionSingleton.getConnection();

        SpringApplication.run(ForumApplication.class, args);

    }

}
