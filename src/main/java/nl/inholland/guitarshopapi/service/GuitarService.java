package nl.inholland.guitarshopapi.service;

import nl.inholland.guitarshopapi.model.Guitar;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuitarService {

    private List<Guitar> guitars;

    public GuitarService(List<Guitar> guitars) {
        this.guitars = guitars;
    }

    public List<Guitar> getAllGuitars() {
        return guitars;
    }

    /* Only returning ID is not proper REST, but it will do for now */
    public Integer addGuitar(Guitar guitar) {
        guitars.add(guitar);
        return guitars.indexOf(guitar);
    }

    public void updateGuitar(Guitar guitar) {
        guitars.stream()
                .filter(g -> g.equals(guitar))
                .findFirst()
                .ifPresentOrElse(
                        g -> guitars.set(guitars.indexOf(g), guitar),
                        () -> {
                            throw new IllegalArgumentException("Guitar not present");
                        }
                );

    }

    public void deleteGuitar(Guitar guitar) {
        guitars.stream()
                .filter(g -> g.equals(guitar))
                .findFirst()
                .ifPresentOrElse(
                        g -> guitars.remove(g),
                        () -> {
                            throw new IllegalArgumentException("Guitar not present");
                        }
                );
    }
}
