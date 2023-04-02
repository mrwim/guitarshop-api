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
}
