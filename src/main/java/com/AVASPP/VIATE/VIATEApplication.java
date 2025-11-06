package com.AVASPP.VIATE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
