package com.ksero.backendkseroapi.steps;

import java.util.Set;
import java.util.logging.Logger;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.ksero.backendkseroapi.ksero.resources.product.CreateProductResource;
import com.ksero.backendkseroapi.security.domain.service.communication.AuthenticateRequest;
import com.ksero.backendkseroapi.security.domain.service.communication.RegisterRequest;
import com.ksero.backendkseroapi.security.resource.AuthenticateResource;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.AssertionFailedError;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class US07Stepdefs {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private CreateProductResource productRequest;
    private String response;
    private String url;

    private void beforeSteps() {
        ResponseEntity<AuthenticateResource> authResponse;
        // Before this you must erase all rows in database table
        RegisterRequest registerRequest = new RegisterRequest(
            "miguel",
            "miguel@hotmail.com",
            "12345",
            "123456789",
            Set.of("ROLE_WHOLESALER")
        );
        this.testRestTemplate.postForEntity("http://localhost:8090/api/v1/users/auth/sign-up", registerRequest, String.class);
        AuthenticateRequest authRequest = new AuthenticateRequest(
            "miguel",
            "12345"
        );
        authResponse = this.testRestTemplate.postForEntity("http://localhost:8090/api/v1/users/auth/sign-in", authRequest, AuthenticateResource.class);
    }

    @Given("que el usuario mayorista desea inscribir un producto")
    public void wholeSalerWantsToCreateProduct() {
        beforeSteps();
    }
    @When("pulse la opción de inscribir producto")
    public void wholeSalerHasPressedCreateProductButton() {
        url = "http://localhost:8090/api/v1/products";
    }
    @Then("aparecerá un formulario el cual deberá completar con la información del producto")
    public void productInputHasEntered() {
        productRequest = new CreateProductResource(
            "vegatables",
            "1kg of different vegetables",
            "photo.png",
            Long.valueOf((long)1),
            Double.valueOf(22.50)
        );
        Assertions.assertTrue(true);
    }
    @When("termine y pulse el botón de finalizar")
    public void productHasCreated() {
        response = this.testRestTemplate.postForObject(url, productRequest, String.class);
    }
    @Then("se le mostrará un mensaje diciendo que el producto se agrego correctamente a su catálogo")
    public void productWasSuccessfullyCreated() {
        System.out.println(response);
        Assertions.assertTrue(response != null);
    }

    // Unhappy Path
    @Then("aparecerá un formulario el cual deberá completar con la información del producto, pero no lo completa en su totalidad")
    public void productInputHasEnteredUncorrectly() {
        this.productRequest = new CreateProductResource(
            "vegatables",
            "1kg of different vegetables",
            "photo.png",
            Long.valueOf((long)1),
            null
        );
    }

    @Then("se le mostrará un mensaje diciendo que falta información por completar")
    public void productFieldsAreMissing() {
        if(response == null) {
            Logger logger = Logger.getLogger("Registro de producto");
            logger.info("Falta información por registrar");
            return;
        }
        throw new AssertionFailedError("Product creation with missing fields must return null as response");
    }

}
