package nl.inholland.guitarshopapi;

import nl.inholland.guitarshopapi.model.Guitar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class GuitarshopApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuitarshopApiApplication.class, args);
	}

	/*Instead of a database we use this bean ... for now */
	@Bean
	public List<Guitar> guitars() {
		/* List.of is immutable, and therefore we should wrap it inside an ArrayList */
		return new ArrayList<>(List.of(
				new Guitar("Fender", "Stratocaster", 1700.00),
				new Guitar("Fender", "Telecaster", 1299.00),
				new Guitar("Gibson", "Les Paul", 2399.00)
		));
	}
}
