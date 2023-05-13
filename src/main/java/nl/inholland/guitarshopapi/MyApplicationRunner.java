package nl.inholland.guitarshopapi;

import jakarta.transaction.Transactional;
import nl.inholland.guitarshopapi.model.Brand;
import nl.inholland.guitarshopapi.model.Guitar;
import nl.inholland.guitarshopapi.model.StockItem;
import nl.inholland.guitarshopapi.repository.BrandRepository;
import nl.inholland.guitarshopapi.repository.GuitarRepository;
import nl.inholland.guitarshopapi.repository.StockItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class MyApplicationRunner implements ApplicationRunner {

    public static final String FENDER = "Fender";
    public static final String GIBSON = "Gibson";
    public static final String IBANEZ = "Ibanez";

    private BrandRepository brandRepository;
    private GuitarRepository guitarRepository;
    private StockItemRepository stockItemRepository;
    @Autowired
    private Random randomizer;

    public MyApplicationRunner(BrandRepository brandRepository, GuitarRepository guitarRepository, StockItemRepository stockItemRepository) {
        this.brandRepository = brandRepository;
        this.guitarRepository = guitarRepository;
        this.stockItemRepository = stockItemRepository;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        brandRepository.saveAll(
                List.of(new Brand(FENDER, "USA"),
                        new Brand(GIBSON, "USA"),
                        new Brand(IBANEZ, "Mexico"
                        )));

        brandRepository.findAll().forEach(System.out::println);

        guitarRepository.saveAll(List.of(
                new Guitar(brandRepository.getBrandByNameIgnoreCase(FENDER).orElseThrow(), "Stratocaster", 1700.00),
                new Guitar(brandRepository.getBrandByNameIgnoreCase(FENDER).orElseThrow(), "Telecaster", 1299.00),
                new Guitar(brandRepository.getBrandByNameIgnoreCase(GIBSON).orElseThrow(), "Les Paul", 2399.00)
        ));

        guitarRepository.findAll().forEach(System.out::println);

        List<StockItem> stockItems = new ArrayList<>();
        guitarRepository.findAll().forEach(
                g -> stockItems.add(new StockItem(g, randomizer.nextInt(15)))
        );

        stockItemRepository.saveAll(stockItems);

        stockItems.forEach(System.out::println);
    }


}
