package api.LoginTests;

import api.components.CreateResponse;
import api.components.Routes;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginAndEmailTest {

    @Order(8)
    @Test
    @DisplayName("Testing API POST request for logging in without e-mail parameter")
    void verifyForbiddenLoginWithoutEmailParameter(){
        Response response = CreateResponse.post("password", Routes.postUserAccount_url);
        //checking body contains the correct error message
        response.jsonPath().get("message").equals("This request method is not supported.");
        response.jsonPath().get("responseCode").equals(405);//and checking response code stored in response body(json) is correct
    }
    @Order(14)
    @Test
    @DisplayName("Testing API GET request for accessing user details by email")
    void GETUserAccountByEmail(){

        //when().get(Routes.getUserAccountByEmail_url).then().body("message",
        //        Matchers.equalTo("Bad request, email parameter is missing in GET request."));

        when().get(Routes.getUserAccountByEmail_url).getBody().print();//prints json content of body

        //checking body contains the correct error message
        assertThat(when().get(Routes.getUserAccountByEmail_url).jsonPath().get("message")
                , Matchers.equalTo("Bad request, email parameter is missing in GET request."));

        assertThat(when().get(Routes.getUserAccountByEmail_url).jsonPath().get("responseCode")
                , Matchers.equalTo(400));//and checking response code stored in response body(json) is correct

    }

}
