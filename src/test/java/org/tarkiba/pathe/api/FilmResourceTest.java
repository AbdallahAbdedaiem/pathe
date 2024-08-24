package org.tarkiba.pathe.api;

import io.quarkus.test.junit.QuarkusTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class FilmResourceTest {

    @Test
    public void test() {
        given()
                .when().get("/film-title/5")
                .then()
                .statusCode(200)
                .body(Matchers.containsString("AFRICAN EGG"));

    }
}
