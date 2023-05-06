package nl.inholland.guitarshopapi.controller;

import nl.inholland.guitarshopapi.model.Brand;
import nl.inholland.guitarshopapi.service.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<Object> getAllBrands() {
        return ResponseEntity.ok().body(brandService.getAllBrands());
    }

    @PostMapping
    public ResponseEntity<Object> addBrand(Brand brand) {
        return ResponseEntity.status(HttpStatus.CREATED).body(brandService.addBrand(brand));
    }
}
