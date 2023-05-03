package nl.inholland.guitarshopapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String country;
    @OneToMany(mappedBy = "brand", fetch = FetchType.EAGER)
    private List<Guitar> guitars = new ArrayList<>();

    public Brand(String name, String country) {
        this.name = name;
        this.country = country;
    }
}
