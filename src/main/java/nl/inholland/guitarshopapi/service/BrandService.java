package nl.inholland.guitarshopapi.service;

import jakarta.persistence.EntityNotFoundException;
import nl.inholland.guitarshopapi.model.Brand;
import nl.inholland.guitarshopapi.repository.BrandRepository;
import org.springframework.stereotype.Service;

@Service
public class BrandService {

    private BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public Brand addBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    public Brand getBrandByName(String brand) {
        return brandRepository.getBrandByNameIgnoreCase(brand).orElseThrow(
                () -> new EntityNotFoundException("Brand " + brand + "not found")
        );
    }
}