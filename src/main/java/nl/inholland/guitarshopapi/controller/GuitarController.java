package nl.inholland.guitarshopapi.controller;

import lombok.extern.java.Log;
import nl.inholland.guitarshopapi.model.dto.ExceptionDTO;
import nl.inholland.guitarshopapi.model.dto.GuitarDTO;
import nl.inholland.guitarshopapi.service.GuitarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("guitars")
@Log
public class GuitarController {

    private GuitarService guitarService;

    public GuitarController(GuitarService guitarService) {
        this.guitarService = guitarService;
    }

    @GetMapping
    public ResponseEntity getAllGuitars() {
        return ResponseEntity.ok(guitarService.getAllGuitars());
    }

    @PostMapping
    public ResponseEntity addGuitar(@RequestBody GuitarDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(guitarService.addGuitar(dto));
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.status(400)
                    .body(this.handleException(400, e));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity getGuitarById(@PathVariable long id) {
        try {
            return ResponseEntity.ok().body(guitarService.getGuitarById(id));
        } catch (Exception e) {
            return this.handleException(404, e);
        }
    }

    private ResponseEntity handleException(int status, Exception e) {
        ExceptionDTO dto = new ExceptionDTO(status, e.getClass().getName(), e.getMessage());
        return ResponseEntity.status(status).body(dto);
    }
}