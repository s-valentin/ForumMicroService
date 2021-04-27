package ro.uaic.info.Forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.io.FileReader;
import java.io.IOException;


@SpringBootApplication
@EnableEurekaClient
public class ForumApplication {

	public static void main(String[] args) {

		try{
			System.getProperties().load(new FileReader("src/main/resources/application.properties"));
		} catch(IOException e){
			e.printStackTrace();
		}
		SpringApplication.run(ForumApplication.class, args);

	}

}
