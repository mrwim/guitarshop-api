package nl.inholland.guitarshopapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
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

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Brand brand;

    @Column(unique = true)
    private String model;
    private double price;

    public Guitar(Brand brand, String model, double price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }
}
