package nl.inholland.guitarshopapi.controller;

import nl.inholland.guitarshopapi.model.dto.ExceptionDTO;
import nl.inholland.guitarshopapi.service.StockItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stocks")
public class StockItemController {

    private StockItemService stockItemService;

    public StockItemController(StockItemService stockItemService) {
        this.stockItemService = stockItemService;
    }

    @GetMapping
    public ResponseEntity getAllStockItems() {
        return ResponseEntity.ok(stockItemService.getAllStockItems());
    }

    @GetMapping("{id}")
    public ResponseEntity getStockItemByGuitarId(@PathVariable long id) {
        try {
            return ResponseEntity.ok().body(stockItemService.getStockItemByGuitarId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ExceptionDTO(404, e.getClass().getName(), e.getMessage()));
        }
    }
}
