package nl.inholland.guitarshopapi.service;

import nl.inholland.guitarshopapi.model.Guitar;
import nl.inholland.guitarshopapi.model.dto.GuitarDTO;
import nl.inholland.guitarshopapi.repository.GuitarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuitarService {

    private GuitarRepository guitarRepository;
    private BrandService brandService;

    public GuitarService(GuitarRepository guitarRepository, BrandService brandService) {
        this.guitarRepository = guitarRepository;
        this.brandService = brandService;
    }

    public List<Guitar> getAllGuitars() {
        return (List<Guitar>) guitarRepository.findAll();
    }

    /* Only returning ID is not proper REST, but it will do for now */
    public Guitar addGuitar(GuitarDTO dto) {
        return guitarRepository.save(this.mapDtoToGuitar(dto));
    }
//
//    public void updateGuitar(Guitar guitar) {
//        guitars.stream()
//                .filter(g -> g.equals(guitar))
//                .findFirst()
//                .ifPresentOrElse(
//                        g -> guitars.set(guitars.indexOf(g), guitar),
//                        () -> {
//                            throw new IllegalArgumentException("Guitar not present");
//                        }
//                );
//
//    }
//
//    public void deleteGuitar(Guitar guitar) {
//        guitars.stream()
//                .filter(g -> g.equals(guitar))
//                .findFirst()
//                .ifPresentOrElse(
//                        g -> guitars.remove(g),
//                        () -> {
//                            throw new IllegalArgumentException("Guitar not present");
//                        }
//                );
//    }

    private Guitar mapDtoToGuitar(GuitarDTO dto) {
        Guitar guitar = new Guitar();
        guitar.setBrand(brandService.getBrandByName(dto.brand()));
        guitar.setModel(dto.model());
        guitar.setPrice(dto.price());
        return guitar;
    }
}
