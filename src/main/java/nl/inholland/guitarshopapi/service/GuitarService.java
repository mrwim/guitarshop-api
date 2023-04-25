package nl.inholland.guitarshopapi.service;

import jakarta.persistence.EntityNotFoundException;
import nl.inholland.guitarshopapi.model.Guitar;
import nl.inholland.guitarshopapi.model.dto.GuitarDTO;
import nl.inholland.guitarshopapi.repository.BrandRepository;
import nl.inholland.guitarshopapi.repository.GuitarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuitarService {

    private GuitarRepository guitarRepository;
    private BrandRepository brandRepository;

    public GuitarService(GuitarRepository guitarRepository) {
        this.guitarRepository = guitarRepository;
    }

    public Guitar addGuitar(GuitarDTO dto) {
        return guitarRepository.save(this.mapDtoToGuitar(dto));
    }

    public Guitar getGuitarById(Long id) {
        return guitarRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Guitar not found")
        );
    }

    public Guitar updateGuitar(Long id, GuitarDTO dto) {
        Guitar guitarToUpdate = guitarRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Guitar not found"));
        guitarToUpdate.setModel(dto.getModel());
        guitarToUpdate.setPrice(dto.getPrice());
        return guitarRepository.save(guitarToUpdate);


    }

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

    public List<Guitar> getAllGuitars() {
        return (List<Guitar>) guitarRepository.findAll();
    }

    private Guitar mapDtoToGuitar(GuitarDTO dto) {
        Guitar newGuitar = new Guitar();
        newGuitar.setBrand(brandRepository.findBrandByName(dto.getBrand()));
        newGuitar.setModel(dto.getModel());
        newGuitar.setPrice(dto.getPrice());
        return newGuitar;
    }

    public void deleteGuitar(Long id) {
        guitarRepository.delete(guitarRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Guitar not found")));
    }
}
