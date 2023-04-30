package api.LoginTests;

import api.components.CreateResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static api.components.Routes.postLoginDetails_url;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VerifyLoginWithValidDetailsTest {

    static Response response;

    // Reading through csv file email and password and parsing them in the request
    @Order(1)
    @TestTemplate
    @DisplayName("Verify user account")
    @ParameterizedTest(name = "{index} - Name: {0}")
    @CsvFileSource(files = "src/test/resources/VerifyUserAccount.csv", numLinesToSkip = 1)
    public void init(String email, String password) {
        response = CreateResponse.post(postLoginDetails_url, "email", email, "password", password);

    }

    @Order(2)
    @Test
    @DisplayName("Test response message should be User exists!")
    public void testResponseMessageShouldBeUserExists() {
        assertThat(response.jsonPath().getString("message"), equalTo("User exists!"));
    }

    @Order(3)
    @Test
    @DisplayName("Test response code should be 200")
    void testResultCodeShouldBe200() {
        assertThat(response.jsonPath().getString("responseCode"), equalTo("200"));
    }

    @Order(4)
    @Test
    @DisplayName("Test the status code should be 200")
    void testTheResponseCodeShouldBe200() {
        assertThat(response.getStatusCode(), equalTo(200));

    }

}
