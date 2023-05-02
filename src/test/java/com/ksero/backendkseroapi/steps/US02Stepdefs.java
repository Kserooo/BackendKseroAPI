package com.ksero.backendkseroapi.steps;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ksero.backendkseroapi.security.domain.service.communication.AuthenticateRequest;
import com.ksero.backendkseroapi.security.domain.service.communication.RegisterRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class US02Stepdefs {
    @Autowired
    protected TestRestTemplate testRestTemplate;
    private ResponseEntity<String> response;
    private AuthenticateRequest request;

    private void beforeSteps() {
        RegisterRequest registerRequest = new RegisterRequest(
            "miguel",
            "miguel@hotmail.com",
            "12345",
            Set.of("ROLE_WHOLESALER")
        );
        this.testRestTemplate.postForEntity("http://localhost:8090/api/v1/users/auth/sign-up", registerRequest.toString(), String.class);
    }

    @Given("que el usuario quiere iniciar sesión e ingresa los datos correctos")
    public void userEnterLogInDataSuccessfully() {
        beforeSteps();
        request = new AuthenticateRequest(
            "miguel",
            "12345"
        );
    }
    @When("pulse iniciar sesión")
    public void userTriesToLogIn() {
        response = this.testRestTemplate.postForEntity("http://localhost:8090/api/v1/users/auth/sign-in", request, String.class);
    }
    @Then("el sistema le da acceso")
    public void userLoggedInSuccessfully() {
        Assertions.assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
    }
}
