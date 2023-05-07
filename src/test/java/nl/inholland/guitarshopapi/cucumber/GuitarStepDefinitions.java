package nl.inholland.guitarshopapi.cucumber;

import com.jayway.jsonpath.JsonPath;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

public class GuitarStepDefinitions extends BaseStepDefinitions {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> response;

    @Given("When the endpoint {string} is available for method {string}")
    public void whenTheEndpointIsAvailableForMethod(String endpoint, String method) {
        response = restTemplate.exchange("/" + endpoint, HttpMethod.OPTIONS, new HttpEntity<>(null, new HttpHeaders()), String.class);
        List<String> options = Arrays.stream(response.getHeaders()
                        .get("Allow")
                        .get(0)
                        .split(","))
                .toList();
        Assertions.assertTrue(options.contains(method.toUpperCase()));
    }


    @When("I retrieve all guitars")
    public void iRetrieveAllGuitars() {
        response = restTemplate.exchange("/guitars", HttpMethod.GET, new HttpEntity<>(null, new HttpHeaders()), String.class);
    }

    @Then("I should have a list of {int} guitars")
    public void iShouldHaveAListOfGuitars(int number) {

        int actual = JsonPath.read(response.getBody(), "$.size()");
        Assertions.assertEquals(number, actual);
    }
}
