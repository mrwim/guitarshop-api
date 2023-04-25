package nl.inholland.guitarshopapi.service;

import nl.inholland.guitarshopapi.model.Brand;
import nl.inholland.guitarshopapi.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    private BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<Brand> getAllBrands() {
        return (List<Brand>) brandRepository.findAll();
    }
}
