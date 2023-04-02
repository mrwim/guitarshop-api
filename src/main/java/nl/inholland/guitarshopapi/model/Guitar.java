package nl.inholland.guitarshopapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Guitar {

    private String brand;
    private String model;
    private double price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guitar guitar = (Guitar) o;
        return Objects.equals(brand, guitar.brand)
                && Objects.equals(model, guitar.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model);
    }
}
