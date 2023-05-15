package nl.inholland.guitarshopapi.configuration;

import nl.inholland.guitarshopapi.util.JwtTokenProvider;
import org.springframework.boot.test.mock.mockito.MockBean;

@org.springframework.boot.test.context.TestConfiguration
public class ApiTestConfiguration {

    @MockBean
    private JwtTokenProvider jwtTokenProvider;
}
