package com.example.demo.cucumber.steps;

import com.example.demo.controller.dto.request.FilterPriceRequest;
import com.example.demo.cucumber.SpringIntegrationTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpStatus;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class StepsDefinitions extends SpringIntegrationTest {

    @When("^the client calls /version$")
    public void the_client_issues_GET_version() throws Throwable {
        executeGet("http://localhost:8082/version");
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
        assertThat("status code is incorrect : " + latestResponse.getBody(), currentStatusCode.value(), is(statusCode));
    }

    @And("^the client receives server version (.+)$")
    public void the_client_receives_server_version_body(String version) /*throws Throwable*/ {
        assertThat(latestResponse.getBody(), is(version));
    }

    @When("^the client calls /prices$")
    public void the_client_calls_Prices() throws Throwable {
        String requestBody = "{\"brandId\":1,\"priceListId\":2,\"productId\":35455,\"applicationDate\": \"2020-06-15T00:00\"}";
        executePost("http://localhost:8082/prices", requestBody);
    }
    @Then("^the client receives a price response$")
    public void the_client_recives_price_response() throws Throwable {
        String expectedBody ="[{\"brandId\":1,\"startDate\":\"2020-06-14T00:00:00\",\"endDate\":\"2020-12-31T23:59:00\",\"priceListId\":1,\"productId\":35455,\"priority\":0,\"price\":35.5,\"curr\":\"EUR\"},{\"brandId\":1,\"startDate\":\"2020-06-14T15:00:00\",\"endDate\":\"2020-06-14T18:30:00\",\"priceListId\":2,\"productId\":35455,\"priority\":0,\"price\":30.5,\"curr\":\"EUR\"},{\"brandId\":1,\"startDate\":\"2020-06-15T00:00:00\",\"endDate\":\"2020-06-15T11:00:00\",\"priceListId\":3,\"productId\":35455,\"priority\":0,\"price\":20.98,\"curr\":\"EUR\"},{\"brandId\":1,\"startDate\":\"2020-06-15T16:00:00\",\"endDate\":\"2020-12-31T23:59:00\",\"priceListId\":4,\"productId\":35455,\"priority\":0,\"price\":38.95,\"curr\":\"EUR\"}]";
        assertThat(latestResponse.getTheResponse().getStatusCode(),is(HttpStatus.OK));
        assertThat(latestResponse.getBody(),is(expectedBody));
    }

}
