package nl.inholland.guitarshopapi.repository;

import nl.inholland.guitarshopapi.model.StockItem;
import org.springframework.data.repository.CrudRepository;

public interface StockItemRepository extends CrudRepository<StockItem, Long> {

    StockItem findStockItemByGuitarId(long id);
}
