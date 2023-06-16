package org.max.lesson3.seminar;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.max.lesson3.AbstractTest;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ConvertAmountsTest extends AbstractTest {

    @Test
    void getConvertAmounts_whenValid_shouldReturn() {
        ConvertAmountsDto response = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("ingredientName", "flour")
                .queryParam("sourceAmount", 2.5)
                .queryParam("sourceUnit", "cups")
                .queryParam("targetUnit", "grams")
                .when()
                .get(getBaseUrl()+"recipes/convert")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(1000l))
                .extract()
                .response()
                .body().as(ConvertAmountsDto.class);

        Assertions.assertEquals(response.getTargetAmount(),312.5);

    }
}
