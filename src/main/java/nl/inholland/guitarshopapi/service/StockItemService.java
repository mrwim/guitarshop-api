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

    private StockItemRepository stockItemRepository;

    public StockItemService(StockItemRepository stockItemRepository) {
        this.stockItemRepository = stockItemRepository;
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

    public StockItem addStockItem(Guitar guitar, int quantity) {
        return stockItemRepository.save(new StockItem(
                guitar, new Random().nextInt(15)
        ));
    }

}
