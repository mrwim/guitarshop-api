package nl.inholland.guitarshopapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Brand brand;

    public Guitar(Brand brand, String model, double price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }
}
