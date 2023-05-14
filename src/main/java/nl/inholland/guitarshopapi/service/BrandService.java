package nl.inholland.guitarshopapi.service;

import jakarta.persistence.EntityNotFoundException;
import nl.inholland.guitarshopapi.model.Brand;
import nl.inholland.guitarshopapi.model.dto.BrandDTO;
import nl.inholland.guitarshopapi.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public Brand addBrand(BrandDTO dto) {
        return brandRepository.save(this.mapDtoToBrand(dto));
    }

    public Brand getBrandByName(String brand) {
        return brandRepository.getBrandByNameIgnoreCase(brand).orElseThrow(
                () -> new EntityNotFoundException("Brand " + brand + "not found")
        );
    }

    public List<Brand> getAllBrands() {
        return (List<Brand>) brandRepository.findAll();
    }

    private Brand mapDtoToBrand(BrandDTO dto) {
        Brand brand = new Brand();
        brand.setName(dto.name());
        brand.setCountry(dto.country());
        return brand;
    }
}
