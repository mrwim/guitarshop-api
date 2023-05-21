package nl.inholland.guitarshopapi.controller;

import jakarta.validation.Valid;
import lombok.extern.java.Log;
import nl.inholland.guitarshopapi.model.Guitar;
import nl.inholland.guitarshopapi.model.dto.GuitarDTO;
import nl.inholland.guitarshopapi.service.GuitarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("guitars")
@Log
public class GuitarController {

    private final GuitarService guitarService;

    public GuitarController(GuitarService guitarService) {
        this.guitarService = guitarService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Object> getAllGuitars(@RequestParam(required = false, defaultValue = "asc") String order) {
        // The result could just as well - or even better - be accomplished by queries on the database itself
        List<Guitar> guitars = guitarService.getAllGuitars();
        Comparator<Guitar> comparator =
                order.startsWith("desc") ? new Comparator<Guitar>() {
                    @Override
                    public int compare(Guitar guitar, Guitar other) {
                        return (int) (other.getPrice() - guitar.getPrice());
                    }
                } : new Comparator<Guitar>() {
                    @Override
                    public int compare(Guitar guitar, Guitar other) {
                        return (int) (guitar.getPrice() - other.getPrice());
                    }
                };
        guitars.sort(comparator);
        return ResponseEntity.ok(guitars);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> addGuitar(@RequestBody @Valid GuitarDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(guitarService.addGuitar(dto));
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getGuitarById(@PathVariable long id) {
        return ResponseEntity.ok().body(guitarService.getGuitarById(id));
    }
}