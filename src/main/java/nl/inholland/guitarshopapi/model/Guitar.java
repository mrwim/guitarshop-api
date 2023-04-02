package nl.inholland.guitarshopapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Guitar {

    private String brand;
    private String model;
    private double price;
}
