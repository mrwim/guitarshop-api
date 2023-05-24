package nl.inholland.guitarshopapi.service;

import jakarta.persistence.EntityNotFoundException;
import nl.inholland.guitarshopapi.model.Guitar;
import nl.inholland.guitarshopapi.model.dto.GuitarDTO;
import nl.inholland.guitarshopapi.repository.GuitarRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class GuitarService {

    private final GuitarRepository guitarRepository;
    private final BrandService brandService;

    public GuitarService(GuitarRepository guitarRepository, BrandService brandService) {
        this.guitarRepository = guitarRepository;
        this.brandService = brandService;
    }

    public List<Guitar> getAllGuitars(String order) {
        List<Guitar> guitars = (List<Guitar>) guitarRepository.findAll();
        Comparator<Guitar> comparator = order.startsWith("desc") ?
                (g1, g2) -> (int) (g2.getPrice() - g1.getPrice())
                : (g1, g2) -> (int) (g1.getPrice() - g2.getPrice());
        guitars.sort(comparator);
        return guitars;
    }

    public Guitar addGuitar(GuitarDTO dto) {
        return guitarRepository.save(this.mapDtoToGuitar(dto));
    }

    private Guitar mapDtoToGuitar(GuitarDTO dto) {
        Guitar guitar = new Guitar();
        guitar.setBrand(brandService.getBrandByName(dto.brand()));
        guitar.setModel(dto.model());
        guitar.setPrice(dto.price());
        return guitar;
    }

    public Guitar getGuitarById(long id) {
        return guitarRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Guitar not found"));
    }
}
