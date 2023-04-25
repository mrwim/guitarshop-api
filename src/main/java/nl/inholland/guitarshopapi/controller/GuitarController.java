package nl.inholland.guitarshopapi.controller;

import jakarta.persistence.EntityNotFoundException;
import nl.inholland.guitarshopapi.model.Guitar;
import nl.inholland.guitarshopapi.model.dto.ExceptionDTO;
import nl.inholland.guitarshopapi.model.dto.GuitarDTO;
import nl.inholland.guitarshopapi.service.GuitarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("guitars")
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
    public ResponseEntity addGuitar(@RequestBody GuitarDTO guitar) {
        try {
            return ResponseEntity.status(201).body(
                    guitarService.addGuitar(guitar)
            );
        } catch (Exception e) {
            // This exposes too much data
            return this.handleException(400, e);
        }
    }

    @GetMapping(value = "{id}")
    public ResponseEntity getGuitarById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(guitarService.getGuitarById(id));
        } catch (EntityNotFoundException enfe) {
            return this.handleException(404, enfe);
        }
    }

    //TODO: Returns no body. Fix that
    @PutMapping("{id}")
    public ResponseEntity updateGuitar(@PathVariable Long id, @RequestBody GuitarDTO dto) {
        try {
            Guitar toUpdate = guitarService.updateGuitar(id, dto);
            return ResponseEntity.status(204).body(toUpdate);
        } catch (Exception e) {
            return this.handleException(400, e);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteGuitar(@PathVariable Long id) {
        try {
            guitarService.deleteGuitar(id);
            return ResponseEntity.status(204).body(null);
        } catch (Exception e) {
            return this.handleException(404, e);
        }
    }

    private ResponseEntity handleException(int status, Exception e) {
        ExceptionDTO dto = new ExceptionDTO(e.getClass().getName(), e.getMessage());
        return ResponseEntity.status(status).body(dto);
    }
}