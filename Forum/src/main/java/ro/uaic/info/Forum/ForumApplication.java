package ro.uaic.info.Forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileReader;
import java.io.IOException;

@SpringBootApplication
public class ForumApplication {

	public static void main(String[] args) {

		try{
			System.getProperties().load(new FileReader("F:\\Facultate\\AN 2\\Sem2\\IP\\ForumMicroService\\Forum\\src\\main\\resources\\application.properties"));
		} catch(IOException e){
			e.printStackTrace();
		}
		SpringApplication.run(ForumApplication.class, args);

	}

}
