package nl.inholland.guitarshopapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@NoArgsConstructor
@Data
public class Brand {

    @GeneratedValue
    @Id
    private long id;

    private String name;
    private String country;

    @OneToMany(mappedBy = "brand")
    private Set<Guitar> guitars;

    public Brand(String name, String country) {
        this.name = name;
        this.country = country;
    }
}
