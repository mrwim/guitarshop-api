package nl.inholland.guitarshopapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Guitar extends StringInstrument {

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
        this();
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    public Guitar() {
        this.numberOfStrings = 6;
    }


    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Guitar price cannot be negative");
        }
        this.price = price;
    }
}
