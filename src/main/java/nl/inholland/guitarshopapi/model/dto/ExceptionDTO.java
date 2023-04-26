package nl.inholland.guitarshopapi.model.dto;

public record ExceptionDTO(int status, String exception, String message) {
}
