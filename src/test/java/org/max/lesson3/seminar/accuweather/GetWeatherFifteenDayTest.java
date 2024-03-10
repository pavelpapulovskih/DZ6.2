package org.max.lesson3.seminar.accuweather;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.max.lesson3.home.accuweather.AccuweatherAbstractTest;
import org.max.lesson3.home.accuweather.weather.Weather;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetWeatherFifteenDayTest extends AccuweatherAbstractTest {

    @Test
    void getWeatherFifteenDay_invalidApiKey_shouldReturn401() {
        given()
                .queryParam("apikey", "invalidApiKey")
                .when()
                .get(getBaseUrl()+"/forecasts/v1/daily/15day/290396")
                .then()
                .statusCode(401);
    }

    @Test
    void getWeatherFifteenDay_invalidLocationKey_shouldReturn404() {
        given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/forecasts/v1/daily/15day/invalidLocationKey")
                .then()
                .statusCode(404);
    }

    @Test
    void getWeatherFifteenDay_missingApiKey_shouldReturn401() {
        given()
                .when()
                .get(getBaseUrl()+"/forecasts/v1/daily/15day/290396")
                .then()
                .statusCode(401);
    }

    @Test
    void getWeatherFifteenDay_invalidBaseUrl_shouldReturn404() {
        given()
                .queryParam("apikey", getApiKey())
                .when()
                .get("https://developer.accuweather.com/accuweather-forecast-api/apis/get/forecasts/v1/daily/15day/%7BlocationKey%7D")
                .then()
                .statusCode(404);
    }
}
