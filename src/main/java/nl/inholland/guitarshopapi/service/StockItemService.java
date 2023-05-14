package nl.inholland.guitarshopapi.service;

import jakarta.persistence.EntityNotFoundException;
import nl.inholland.guitarshopapi.model.Guitar;
import nl.inholland.guitarshopapi.model.StockItem;
import nl.inholland.guitarshopapi.repository.StockItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class StockItemService {

    private final StockItemRepository stockItemRepository;
    private Random randomizer;

    public StockItemService(StockItemRepository stockItemRepository, Random randomizer) {
        this.stockItemRepository = stockItemRepository;
        this.randomizer = randomizer;
    }

    public List<StockItem> getAllStockItems() {
        return (List<StockItem>) stockItemRepository.findAll();
    }

    public StockItem getStockItemByGuitarId(long id) {
        return stockItemRepository.findStockItemByGuitarId(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No stock available for Guitar with id " + id
                ));


    }

    public StockItem addStockItem(Guitar guitar) {
        return stockItemRepository.save(new StockItem(
                guitar, randomizer.nextInt(15)
        ));
    }

}
