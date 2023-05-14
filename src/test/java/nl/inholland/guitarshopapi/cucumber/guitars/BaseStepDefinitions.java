package nl.inholland.guitarshopapi.cucumber.guitars;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
public class BaseStepDefinitions {

    public static final String VALID_USER = "user";
    public static final String VALID_ADMIN = "admin";
    public static final String VALID_PASSWORD = "password";
    public static final String INVALID_USERNAME = "bla";
    public static final String INVALID_PASSWORD = "invalid";
}
