package nl.inholland.guitarshopapi.controller;

import jakarta.validation.Valid;
import nl.inholland.guitarshopapi.model.Brand;
import nl.inholland.guitarshopapi.model.dto.BrandDTO;
import nl.inholland.guitarshopapi.service.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("brands")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping(params = "name")
    public ResponseEntity<Object> getBrandByName(@RequestParam String name) {
        return ResponseEntity.ok().body(brandService.getBrandByName(name));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Object> getAllBrands() {
        return ResponseEntity.ok().body(brandService.getAllBrands());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> createNewBrand(@Valid @RequestBody BrandDTO dto) {
        Brand brand = brandService.addBrand(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(brand);
    }
}
