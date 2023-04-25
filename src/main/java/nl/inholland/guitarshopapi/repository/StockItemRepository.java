package nl.inholland.guitarshopapi.repository;

import nl.inholland.guitarshopapi.model.StockItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockItemRepository extends CrudRepository<StockItem, Long> {

    StockItem findStockItemByGuitarId(long id);
}
