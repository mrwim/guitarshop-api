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

import java.util.Collections;
import java.util.Objects;

@RestController
@RequestMapping("guitars")
@Log
public class GuitarController {

    private final GuitarService guitarService;

    public GuitarController(GuitarService guitarService) {
        this.guitarService = guitarService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllGuitars() {
        return ResponseEntity.ok(guitarService.getAllGuitars());
    }

    @PostMapping
    public ResponseEntity<Object> addGuitar(@RequestBody GuitarDTO dto) {
        if (Objects.isNull(dto) ||
                this.isDtoEmpty(dto)) {
            return ResponseEntity.status(400).body(
                    Collections.singletonMap("error", "Object is null")
            );
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(guitarService.addGuitar(dto));
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getGuitarById(@PathVariable long id) {
        return ResponseEntity.ok().body(guitarService.getGuitarById(id));
    }

    private ResponseEntity<Object> handleException(int status, Exception e) {
        ExceptionDTO dto = new ExceptionDTO(status, e.getClass().getName(), e.getMessage());
        return ResponseEntity.status(status).body(dto);
    }

    private boolean isDtoEmpty(GuitarDTO dto) {
        return dto.brand() == null &&
                dto.price() == 0 &&
                dto.model() == null;

    }
}