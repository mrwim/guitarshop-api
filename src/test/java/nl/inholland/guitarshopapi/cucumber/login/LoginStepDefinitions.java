package nl.inholland.guitarshopapi.cucumber.login;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import nl.inholland.guitarshopapi.configuration.SSLUtils;
import nl.inholland.guitarshopapi.cucumber.guitars.BaseStepDefinitions;
import nl.inholland.guitarshopapi.model.dto.LoginDTO;
import nl.inholland.guitarshopapi.model.dto.TokenDTO;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Log
public class LoginStepDefinitions extends BaseStepDefinitions {

    private static final String LOGIN_ENDPOINT = "/members";
    private LoginDTO loginDTO;
    private String token;
    private final HttpHeaders httpHeaders = new HttpHeaders();
    private ResponseEntity<String> response;


    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Before
    public void init() {
        SSLUtils.turnOffSslChecking();
        log.info("Turned off SSL checking");
    }

    @Given("I have a valid login object with valid user and valid password")
    public void iHaveAValidLoginObjectWithUserAndPassword() {
        loginDTO = new LoginDTO(VALID_USER, VALID_PASSWORD);
    }

    @When("I call the application login endpoint")
    public void iCallTheApplicationLoginEndpoint() throws JsonProcessingException {
        httpHeaders.add("Content-Type", "application/json");
        response = restTemplate.exchange(
                LOGIN_ENDPOINT,
                HttpMethod.POST,
                new HttpEntity<>(
                        objectMapper.writeValueAsString(loginDTO),
                        httpHeaders), String.class
        );
    }

    @Then("I receive a token")
    public void iReceiveAToken() throws JsonProcessingException {
        token = objectMapper.readValue(response.getBody(), TokenDTO.class).token();
        Assertions.assertNotNull(token);
    }

    @Given("I have a valid username but invalid password")
    public void iHaveAValidUsernameButInvalidPassword() {
        loginDTO = new LoginDTO(VALID_USER, INVALID_PASSWORD);
    }

    @Then("I receive http status {int}")
    public void iReceiveHttpStatus(int status) {
        Assertions.assertEquals(status, response.getStatusCode().value());
    }

    @Given("I have an invalid username and valid password")
    public void iHaveAnInvalidUsernameAndValidPassword() {
        loginDTO = new LoginDTO(INVALID_USERNAME, VALID_PASSWORD);
    }
}
