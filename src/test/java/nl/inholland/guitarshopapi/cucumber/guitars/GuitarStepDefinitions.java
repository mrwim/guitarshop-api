package nl.inholland.guitarshopapi.cucumber.guitars;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import nl.inholland.guitarshopapi.configuration.SSLUtils;
import nl.inholland.guitarshopapi.model.Guitar;
import nl.inholland.guitarshopapi.model.dto.LoginDTO;
import nl.inholland.guitarshopapi.model.dto.TokenDTO;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Log
public class GuitarStepDefinitions extends BaseStepDefinitions {

    HttpHeaders httpHeaders = new HttpHeaders();
    @Autowired
    private TestRestTemplate restTemplate;
    private ResponseEntity<String> response;
    @Autowired
    private ObjectMapper mapper;

    private String token;


    @SneakyThrows
    @Before
    public void init() {
        SSLUtils.turnOffSslChecking();
        log.info("Turned off SSL checking");
    }

    @Given("The endpoint for {string} is available for method {string}")
    public void theEndpointForIsAvailableForMethod(String endpoint, String method) {

        response = restTemplate.exchange(
                "/" + endpoint,
                HttpMethod.OPTIONS,
                new HttpEntity<>(
                        null,
                        httpHeaders),
                String.class);
        List<String> options = Arrays.stream(Objects.requireNonNull(response.getHeaders()
                        .get("Allow"))
                .get(0)
                .split(",")).toList();

        Assertions.assertTrue(options.contains(method.toUpperCase()));

    }

    @When("I retrieve all guitars")
    public void iRetrieveAllGuitars() {
        httpHeaders.clear();
        httpHeaders.add("Authorization", "Bearer " + token);
        response = restTemplate.exchange(
                "/guitars",
                HttpMethod.GET,
                new HttpEntity<>(null,
                        httpHeaders),
                String.class
        );
    }

    @Then("I get a list of {int} guitars")
    public void iGetAListOfGuitars(int expected) {
        String body = (String) response.getBody();
        int actual = JsonPath.read(body, "$.size()");
        Assertions.assertEquals(expected, actual);
    }


    @And("I get http status {int}")
    public void iGetHttpStatus(int status) {
        int actual = response.getStatusCode().value();
        Assertions.assertEquals(status, actual);
    }

    @When("I provide a guitar with brand name {string} and model name {string}")
    public void iProvideAGuitarWithBrandNameAndModelName(String brand, String model) throws JsonProcessingException {
        httpHeaders.add("Content-Type", "application/json");
        httpHeaders.add("Authorization", "Bearer " + token);
        response = restTemplate.exchange(
                "/guitars",
                HttpMethod.POST,
                new HttpEntity<>(
                        """
                                {
                                "brand": "Fender",
                                "model": "jaguar"
                                }
                                     """, httpHeaders),
                String.class);
    }

    @And("The price of the guitar is {int}")
    public void thePriceOfTheGuitarIs(int price) throws JsonProcessingException {
        String body = response.getBody();
        Guitar guitar = mapper.readValue(body, Guitar.class);
        double actual = guitar.getPrice();
        Assertions.assertEquals(price, actual);
    }

    @Given("I log in as user")
    public void iLogInAsUser() throws JsonProcessingException {
        httpHeaders.clear();
        httpHeaders.add("Content-Type", "application/json");
        LoginDTO loginDTO = new LoginDTO(VALID_USER, VALID_PASSWORD);
        token = getToken(loginDTO);
    }

    private String getToken(LoginDTO loginDTO) throws JsonProcessingException {
        response = restTemplate
                .exchange("/members",
                        HttpMethod.POST,
                        new HttpEntity<>(mapper.writeValueAsString(loginDTO), httpHeaders), String.class);
        TokenDTO tokenDTO = mapper.readValue(response.getBody(), TokenDTO.class);
        return tokenDTO.token();
    }

    @Given("I log in as admin")
    public void iLogInAsAdmin() throws JsonProcessingException {
        httpHeaders.clear();
        httpHeaders.add("Content-Type", "application/json");
        LoginDTO loginDTO = new LoginDTO(VALID_ADMIN, VALID_PASSWORD);
        token = getToken(loginDTO);
    }
}
