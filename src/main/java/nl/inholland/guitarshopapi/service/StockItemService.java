package nl.inholland.guitarshopapi.service;

import nl.inholland.guitarshopapi.model.StockItem;
import nl.inholland.guitarshopapi.repository.StockItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockItemService {

    private StockItemRepository stockItemRepository;

    public StockItemService(StockItemRepository stockItemRepository) {
        this.stockItemRepository = stockItemRepository;
    }

    public List<StockItem> allStockItems() {
        return (List<StockItem>) stockItemRepository.findAll();
    }

    public StockItem getStockItemByGuitarId(long id) {
        return stockItemRepository.findStockItemByGuitarId(id);
    }
}
