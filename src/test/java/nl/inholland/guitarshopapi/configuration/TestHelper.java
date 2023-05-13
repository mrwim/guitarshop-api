package nl.inholland.guitarshopapi.configuration;

import java.util.stream.IntStream;

public class TestHelper {

    public static String returnStringOfSize(int size) {
        return IntStream.iterate(1, i -> i + 1)
                .limit(100)
                .map(i -> 'x')
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }
}
