package nl.inholland.guitarshopapi.configuration;

import jakarta.transaction.Transactional;
import nl.inholland.guitarshopapi.model.Brand;
import nl.inholland.guitarshopapi.model.dto.GuitarDTO;
import nl.inholland.guitarshopapi.service.BrandService;
import nl.inholland.guitarshopapi.service.GuitarService;
import nl.inholland.guitarshopapi.service.StockItemService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Random;

@Configuration
@Transactional
public class ApplicationDataInitializer implements ApplicationRunner {

    private GuitarService guitarService;
    private StockItemService stockItemService;
    private BrandService brandService;
    private static final String FENDER = "Fender";
    private static final String GIBSON = "Gibson";
    private static final String IBANEZ = "Ibanez";
    private Random randomizer;

    public ApplicationDataInitializer(GuitarService guitarService, StockItemService stockItemService, BrandService brandService, Random randomizer) {
        this.guitarService = guitarService;
        this.stockItemService = stockItemService;
        this.brandService = brandService;
        this.randomizer = randomizer;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<Brand> brands = List.of(
                new Brand(FENDER, "USA"),
                new Brand(GIBSON, "USA"),
                new Brand(IBANEZ, "Mexico")
        );

        brands.forEach(
                brand -> brandService.addBrand(brand)
        );

        List.of(
                new GuitarDTO(FENDER, "Stratocaster", 1700.00),
                new GuitarDTO(FENDER, "Telecaster", 1299.00),
                new GuitarDTO(GIBSON, "Les Paul", 2399.00)
        ).forEach(
                dto -> guitarService.addGuitar(dto)
        );

        guitarService.getAllGuitars().forEach(System.out::println);

        guitarService
                .getAllGuitars()
                .forEach(guitar -> stockItemService.addStockItem(guitar, randomizer.nextInt(15)));

        stockItemService.getAllStockItems().forEach(System.out::println);
    }
}
