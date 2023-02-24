package nrifintech.busMangementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("nrifintech")
public class BusMangementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusMangementSystemApplication.class, args);
	}

}
