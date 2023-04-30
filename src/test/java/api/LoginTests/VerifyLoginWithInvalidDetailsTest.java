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
public class VerifyLoginWithInvalidDetailsTest {

    static Response response;

    // Reading through the incorrect csv file email and password and parsing them in the request
    @Order(1)
    @TestTemplate
    @DisplayName("Trying to Verify user account")
    @ParameterizedTest(name = "{index} - Name: {0}")
    @CsvFileSource(files = "src/test/resources/VerifyUserAccount.csv", numLinesToSkip = 1)
    public void init(String email, String password) {
        response = CreateResponse.post(postLoginDetails_url, "email","bob@bob.com", "password",  "12345" );
//        response = given()
//                .contentType("application/x-www-form-urlencoded")
//                .formParams("email", "bob@bob.com", "password", "12345")
//                .post(postLoginDetails_url);
    }

    @Order(2)
    @Test
    @DisplayName("Test response message should be User does not exist!")//message "User not found!" should appear
    public void testResponseMessageShouldBeUserDoesNotExist() {
        assertThat(response.jsonPath().getString("message"), equalTo("User not found!"));
    }

    @Order(3)
    @Test
    @DisplayName("Test response code should be 404")//incorrect details should initiate a 404 not found
    void testResultCodeShouldBe404() {
        assertThat(response.jsonPath().getString("responseCode"), equalTo("404"));
    }

    @Order(4)
    @Test
    @DisplayName("Test the status code should be 200")
    void testTheResponseCodeShouldBe404() {
        assertThat(response.getStatusCode(), equalTo(200));//establishing connection

    }


}
