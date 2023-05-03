package nl.inholland.guitarshopapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Guitar {

    @Column(unique = true)
    private String model;
    private double price;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties("guitars")
    private Brand brand;

    public Guitar(Brand brand, String model, double price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }
}
