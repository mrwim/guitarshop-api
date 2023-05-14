package nl.inholland.guitarshopapi.model.dto;

import jakarta.validation.constraints.NotBlank;

public record BrandDTO(@NotBlank String name, String country) {
}
