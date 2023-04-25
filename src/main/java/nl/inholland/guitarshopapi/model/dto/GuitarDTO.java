package nl.inholland.guitarshopapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GuitarDTO {
    private String brand;
    private String model;
    private double price;
}
