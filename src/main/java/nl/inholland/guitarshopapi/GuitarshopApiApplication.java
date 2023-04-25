package nl.inholland.guitarshopapi;

import nl.inholland.guitarshopapi.model.Guitar;
import nl.inholland.guitarshopapi.model.StockItem;
import nl.inholland.guitarshopapi.repository.GuitarRepository;
import nl.inholland.guitarshopapi.repository.StockItemRepository;
import org.springframework.boot.ApplicationRunner;
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

	@Bean
	public ApplicationRunner runner(GuitarRepository guitarRepository, StockItemRepository stockItemRepository) {
		return args -> {
			guitarRepository.saveAll(List.of(
					new Guitar("Fender", "Stratocaster", 1700.00),
					new Guitar("Fender", "Telecaster", 1299.00),
					new Guitar("Gibson", "Les Paul", 2399.00)
			));

			guitarRepository.findAll().forEach(System.out::println);

			List<StockItem> stockItems = new ArrayList<>();
			guitarRepository.findAll().forEach(
					g -> stockItems.add(new StockItem(g, 10))
			);

			stockItemRepository.saveAll(stockItems);

			stockItems.forEach(System.out::println);
		};
	}
}