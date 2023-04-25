package nl.inholland.guitarshopapi;

import jakarta.transaction.Transactional;
import nl.inholland.guitarshopapi.model.Brand;
import nl.inholland.guitarshopapi.model.Guitar;
import nl.inholland.guitarshopapi.model.StockItem;
import nl.inholland.guitarshopapi.repository.BrandRepository;
import nl.inholland.guitarshopapi.repository.GuitarRepository;
import nl.inholland.guitarshopapi.repository.StockItemRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyApplicationRunner implements ApplicationRunner {

    private BrandRepository brandRepository;
    private GuitarRepository guitarRepository;
    private StockItemRepository stockItemRepository;

    public MyApplicationRunner(BrandRepository brandRepository, GuitarRepository guitarRepository, StockItemRepository stockItemRepository) {
        this.brandRepository = brandRepository;
        this.guitarRepository = guitarRepository;
        this.stockItemRepository = stockItemRepository;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        brandRepository.saveAll(
                List.of(new Brand("Fender", "USA"),
                        new Brand("Gibson", "USA"),
                        new Brand("Ibanez", "Japan"
                        )));

        brandRepository.findAll().forEach(System.out::println);

        guitarRepository.saveAll(List.of(
                new Guitar(brandRepository.findBrandByName("Fender"), "Stratocaster", 1700.00),
                new Guitar(brandRepository.findBrandByName("Fender"), "Telecaster", 1299.00),
                new Guitar(brandRepository.findBrandByName("Gibson"), "Les Paul", 2399.00)
        ));

        guitarRepository.findAll().forEach(System.out::println);

        List<StockItem> stockItems = new ArrayList<>();
        guitarRepository.findAll().forEach(
                g -> stockItems.add(new StockItem(g, 10))
        );

        stockItemRepository.saveAll(stockItems);

        stockItems.forEach(System.out::println);

        brandRepository.findAll().forEach(System.out::println);
    }
}
