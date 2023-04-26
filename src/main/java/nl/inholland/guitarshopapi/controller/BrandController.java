package nl.inholland.guitarshopapi.controller;

import nl.inholland.guitarshopapi.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("brands")
public class BrandController {

    private BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping(params = "name")
    public ResponseEntity getBrandByName(@RequestParam String name) {
        return ResponseEntity.ok().body(brandService.getBrandByName(name));
    }

    @GetMapping
    public ResponseEntity getAllBrands() {
        return ResponseEntity.ok().body(brandService.getAllBrands());
    }
}
