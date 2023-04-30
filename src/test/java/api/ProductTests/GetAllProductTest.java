package api.ProductTests;

import api.components.CreateResponse;
import api.components.Routes;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class GetAllProductTest {
   public static  Response response;
    @BeforeAll
    public static void init() {
        response = CreateResponse.get(Routes.getProducts_url);
    }

        @Test
        @DisplayName("Verify that all products are returned with the expected information")
        public void verifyThatAllProductsAreReturnedWithTheExpectedInformation() {
            // Set base URI for the API endpoint
          //  RestAssured.baseURI = "https://automationexercise.com/api/productsList";

//            Random rand = new Random();
//
//            List<Integer> listOfId = response.jsonPath().getList("products.id");
//            int randomId = listOfId.get(rand.nextInt(listOfId.size()));
//
//            System.out.println(response.getBody().asString());

            // Verify the response code is 200 OK
            //Assert.assertEquals(response.getStatusCode(), 200);

            // Verify the response JSON contains the "products" array
            assertThat(response.jsonPath().getInt("products.id[0]"),equalTo(1));
            assertThat(response.jsonPath().getString("products.name[0]"),equalTo("Blue Top"));
            assertThat(response.jsonPath().getString("products.price[0]"),equalTo("Rs. 500"));
            assertThat(response.jsonPath().getString("products.brand[0]"),equalTo("Polo"));
            assertThat(response.jsonPath().getString("products.category.usertype.usertype[0]"),equalTo("Women"));
            assertThat(response.jsonPath().getString("products.category.category[0]"),equalTo("Tops"));


            // (Optional) Print the response JSON to the console for debugging
            //System.out.println(response.getBody().asString());
        }


        @Test
        @DisplayName("Test response code")
        public void testResponseCode() {

            assertThat(
                    response.jsonPath().
                    getInt("responseCode"),
                    equalTo(200));

        }


    @Test
    @DisplayName("Check size of the list should be: " + 34)
    void checkSizeOfTheList() {
        List<String> productNames = response.jsonPath().getList("products.name");
        assertThat(productNames.size(), equalTo(34));

        }

    @Nested
    @DisplayName("Testing headear and status code")
    class testingHeader {

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






