package nl.inholland.guitarshopapi.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GuitarTest {

    private Guitar guitar;

    @BeforeEach
    void init() {
        guitar = new Guitar();
    }

    @Test
    void newGuitarShouldNotBeNull() {
        Assertions.assertNotNull(guitar);
    }

    @Test
    void setGuitarPriceToBelowZeroShouldThrowIllegalArgumentException() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> guitar.setPrice(-1));
        Assertions.assertEquals("Guitar price cannot be negative", exception.getMessage());
    }

    @Test
    void numberOfStringsShouldBeSix() {
        Assertions.assertEquals(6, guitar.getNumberOfStrings());
    }

    @Test
    void createGuitarWithParametersShouldGiveNumberOfStringsAsSix() {
        guitar = new Guitar(
                new Brand(
                        "Fender", "USA"
                ), "Stratocaster", 19999
        );
        Assertions.assertEquals(6, guitar.numberOfStrings);
    }
}