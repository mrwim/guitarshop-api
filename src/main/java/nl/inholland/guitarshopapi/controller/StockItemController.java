package nl.inholland.guitarshopapi.controller;

import nl.inholland.guitarshopapi.service.StockItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stocks")
public class StockItemController {

    private final StockItemService stockItemService;

    public StockItemController(StockItemService stockItemService) {
        this.stockItemService = stockItemService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllStockItems() {
        return ResponseEntity.ok(stockItemService.getAllStockItems());
    }

    @GetMapping("guitars/{id}")
    public ResponseEntity<Object> getStockItemByGuitarId(@PathVariable long id) {
        return ResponseEntity.ok().body(stockItemService.getStockItemByGuitarId(id));
    }

}
