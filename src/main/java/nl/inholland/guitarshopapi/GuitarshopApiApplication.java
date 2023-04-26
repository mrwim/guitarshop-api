package nl.inholland.guitarshopapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class GuitarshopApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuitarshopApiApplication.class, args);
	}

	@Bean
	public Random randomizer() {
		return new Random();
	}
}
