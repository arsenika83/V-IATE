package com.AVASPP.VIATE;

import com.AVASPP.VIATE.entity.user.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class VIATEApplication {

	public static void main(String[] args) {
		SpringApplication.run(VIATEApplication.class, args);
		/*ApplicationContext context = new AnnotationConfigApplicationContext("app/src/main/java/com/AVASPP/VIATE/entity");

		User user = new User();
		for (int i = 0; i < 9; i++) {
			System.out.print(user.getUser());
			System.out.println(" " + i);
		}*/


	}

}
