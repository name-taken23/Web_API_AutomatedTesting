package api.ProductTests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static api.components.Routes.postSearchProduct_url;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import api.components.CreateResponse;

public class EmptySearchParmaProducts {

    private static Response response;

    @BeforeAll
    public static void init() {
        response = CreateResponse.post(postSearchProduct_url, "products", "");
//        response = given().log().all().contentType("application/x-www-form-urlencoded")
//                .formParams("products", "").post(postSearchProduct_url);
//        response = given().log().all().when().post(postSearchProduct_url + "?products=");


    }

    @Test
    @DisplayName("Check the Content Type")
    void checkTheContentType() {
        response
                .then()
                .assertThat()
                .contentType(ContentType.HTML);
    }

    @Nested
    @DisplayName("Tests for the body")
    class TestsForTheBody {
        @Test
        @DisplayName("Test Response Code")
        void testResponseCode() {
            assertThat(response.jsonPath().getString("message"),
                    equalTo("Bad request, search_product parameter is missing in POST request."));

        }
        @Test
        @DisplayName("Test the Response Message")
        void testTheResponseMessage() {
            assertThat(response.jsonPath().getInt("responseCode"), equalTo(400));

        }

        


    }

    @Nested
    @DisplayName("Testing Headers and Status Code")
    class TestingHeadersAndStatusCode {

        @Test
        @DisplayName("Testing Status Code")
        void testingStatusCode() {
            response
                    .then()
                    .assertThat()
                    .statusCode(200);
        }

        @Test
        @DisplayName("Test The Amount Of Headers")
        void testTheAmountOfHeaders() {
            int sizeOfHeaders = response.getHeaders().size();
            MatcherAssert.assertThat(sizeOfHeaders, is(18));

        }

        @Test
        @DisplayName("Test Server Name")
        void testServerName() {
            response
                    .then()
                    .assertThat()
                    .header("server", "cloudflare");

        }
    }
}


