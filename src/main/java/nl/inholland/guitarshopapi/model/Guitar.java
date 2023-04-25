package nl.inholland.guitarshopapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"brand", "model"}))
public class Guitar {

    @Id
    @GeneratedValue
    private Long id;
    private String brand;
    private String model;
    private double price;

    public Guitar(String brand, String model, double price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }
}
