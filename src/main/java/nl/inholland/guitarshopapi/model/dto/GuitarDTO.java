package nl.inholland.guitarshopapi.model.dto;

import jakarta.validation.constraints.NotBlank;

public record GuitarDTO(@NotBlank String brand, String model, double price) {
}
