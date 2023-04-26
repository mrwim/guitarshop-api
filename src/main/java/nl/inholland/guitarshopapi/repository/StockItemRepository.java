package nl.inholland.guitarshopapi.repository;

import nl.inholland.guitarshopapi.model.StockItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockItemRepository extends CrudRepository<StockItem, Long> {
    Optional<StockItem> findStockItemByGuitarId(long id);
}
