package nl.inholland.guitarshopapi.controller;

import nl.inholland.guitarshopapi.model.Guitar;
import nl.inholland.guitarshopapi.service.GuitarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

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
    public ResponseEntity addGuitar(@RequestBody Guitar guitar) {
        return ResponseEntity.status(201).body(
                Collections.singletonMap("id", guitarService.addGuitar(guitar))
        );
    }
}
