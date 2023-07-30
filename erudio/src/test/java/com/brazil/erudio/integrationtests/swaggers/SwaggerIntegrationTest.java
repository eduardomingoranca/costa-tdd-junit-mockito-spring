package com.brazil.erudio.integrationtests.swaggers;

import com.brazil.erudio.integrationtests.testcontainers.AbstractIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.brazil.erudio.configs.TestConfigs.SERVER_PORT;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(webEnvironment = DEFINED_PORT)
class SwaggerIntegrationTest extends AbstractIntegrationTest {

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("Should Display SwaggerUI Page")
    @Test
    void testShouldDisplaySwaggerUIPage() {
        String content = given()
                .basePath("/swagger-ui/index.html")
                .port(SERVER_PORT)
                .when()
                    .get()
                .then()
                    .statusCode(200)
                .extract()
                    .body()
                        .asString();

        assertTrue(content.contains("Swagger UI"));
    }

}
