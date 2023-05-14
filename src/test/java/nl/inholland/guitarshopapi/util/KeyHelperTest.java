package nl.inholland.guitarshopapi.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.Key;

class KeyHelperTest {

    @Test
    void testIfKeyIsReadFromKeystore() throws Exception {
        String keystore = "inholland.p12";
        String password = "inholland";
        String alias = "inholland";
        Key key = KeyHelper.getPrivateKey(alias, keystore, password);
        Assertions.assertNotNull(key);
    }

}