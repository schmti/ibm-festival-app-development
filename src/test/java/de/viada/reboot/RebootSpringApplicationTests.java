package de.viada.reboot;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class RebootSpringApplicationTests {

    @LocalServerPort
    private Integer port;

    @Test
    void contextLoads() {
    }

    @Test
    public void demoReisekostenAreReturnedWithExpectedValues() {
        when()
                .get("http://localhost:" + port + "/reisekosten/demo")
                .then()
                .statusCode(200)
                .body(
                        "id", equalTo(35),
                        "erwartetBrutto", equalTo(265.43f),
                        "erwartetZuschlag", equalTo(0.00f),
                        "zeitraumAb", equalTo("2018-07-20"),
                        "zeitraumBis", equalTo("2018-07-21"),
                        "einreichDatum", notNullValue());
    }

    @Test
    public void integrationTests() {
        shouldBeAbleToAddAndRetrieveReisekosten();
        shouldUseCorrectValueFromConfigurationForZuschlag();
    }

    public void shouldBeAbleToAddAndRetrieveReisekosten() {
        final String testDate1 = "2018-05-06";
        final String testDate2 = "2018-05-07";

        given().
                contentType("application/json").
                body(String.format("{\n" +
                        "\t\"einreichDatum\": \"%s\",\n" +
                        "\t\"zeitraumAb\": \"%s\",\n" +
                        "\t\"zeitraumBis\": \"%s\",\n" +
                        "\t\"zuschlagart\": \"%s\"\n" +
                        "}", testDate1, testDate1, testDate2, "KEIN")).
                when().
                post("http://localhost:" + port + "/reisekosten").
                then().
                statusCode(200);

        when().
                get("http://localhost:" + port + "/reisekosten").
                then().
                body("", hasSize(1), "get(0).einreichDatum", equalTo(testDate1), "get(0).id", notNullValue());
    }

    public void shouldUseCorrectValueFromConfigurationForZuschlag() {
        final String testDate1 = "2018-05-06";
        final String testDate2 = "2018-05-07";

        given().
                contentType("application/json").
                body(String.format("{\n" +
                        "\t\"einreichDatum\": \"%s\",\n" +
                        "\t\"zeitraumAb\": \"%s\",\n" +
                        "\t\"zeitraumBis\": \"%s\",\n" +
                        "\t\"zuschlagart\": \"%s\"\n" +
                        "}", testDate1, testDate1, testDate2, "FERN")).
                when().
                post("http://localhost:" + port + "/reisekosten").
                then().
                statusCode(200);

        when().
                get("http://localhost:" + port + "/reisekosten").
                then().
                body("", hasSize(2),
                        "get(1).erwartetZuschlag", equalTo(100.0f),
                        "get(1).id", notNullValue());
    }
}
