package nl.inholland.guitarshopapi.controller;

import nl.inholland.guitarshopapi.service.StockItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("stocks")
public class StockItemController {

    private StockItemService stockItemService;

    public StockItemController(StockItemService stockItemService) {
        this.stockItemService = stockItemService;
    }

    @GetMapping
    public ResponseEntity getAllStockItems() {
        return ResponseEntity.ok(stockItemService.allStockItems());
    }

    @GetMapping("/guitars/{id}")
    public ResponseEntity getStockItemByGuitarId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(stockItemService.getStockItemByGuitarId(id));
        } catch (Exception e) {
            return ResponseEntity.status(404)
                    .body(Collections.singletonMap("message", "Stockitem not found containing guitar with id " + id));
        }
    }
}
