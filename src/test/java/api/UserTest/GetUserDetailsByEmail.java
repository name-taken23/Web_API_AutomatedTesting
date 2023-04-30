package api.UserTest;

import api.components.CreateResponse;
import api.components.Routes;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
//import static org.hamcrest.MatcherAssert.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetUserDetailsByEmail {




    private static ResponseSpecification responseSpecification;
    private static Response response;

    public static Response responseWithVariable;

    public static String query = "?email";

    @BeforeAll
    public static void createResponseSpecification(){
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

    }


    @BeforeAll
    public static void init(){
        response = CreateResponse.get(Routes.getUserAccountByEmail_url);
        responseWithVariable = CreateResponse.get(Routes.getUserAccountByEmail_url, query);
        //url one gets a response code 200. the other does not
       // response = get("https://automationexercise.com/api/getUserDetailByEmail");

    }


   @Nested
   public class StatusCheckers{

       //test status code
       @Test
       @DisplayName("Check status code is correct")
       public void checkStatusCodeIsCorrect() {
           response
                   .then()
                   .assertThat()
                   .statusCode(200);
       }

       @Test
       @DisplayName("Test response code is correct")
       public void testResponseCodeIsCorrect() {
           assertThat(
                   responseWithVariable
                           .jsonPath()
                           .getInt("responseCode"), equalTo(200));
           }

       @Test
       @DisplayName("Test that response code is 400 when no email parameters are inserted")
       public void testThatResponseCodeIs400WhenNoEmailParametersAreInserted() {
           assertThat(
                   response
                           .jsonPath()
                           .getInt("responseCode"), equalTo(400));
           }
   }


   @Nested
   public class HeaderTests{
        @Test
        @DisplayName("Test header size is always 18")
        public void testHeaderSizeIsAlways18() {

            int sizeOfHeaders = response.getHeaders().size();
            MatcherAssert.assertThat(sizeOfHeaders, is(18));

            }

        @Test
        @DisplayName("Check the server is cloudflare")
        public void checkTheServerIsCloudflare() {


            response
                    .then()
                    .assertThat()
                    .header("Server", "cloudflare");
            }    
   }


   @Nested
   public class TestDataGotten{

       @Test
       @DisplayName("JIR-12 Check ID is gotten from params")
       public void jir12CheckIdIsGottenFromParams() {
           //when email parameter is in the URL, user id will return this value
           assertThat(
                   responseWithVariable
                           .jsonPath()
                           .getString("user.id"), equalTo("17867"));
       }

       @Test
       @DisplayName("JIR-12 Check name is gotten")
       public void jir12CheckNameIsGotten() {
           assertThat(
                   responseWithVariable
                           .jsonPath()
                           .getString("user.name"),equalTo("Rana"));
           }




   }

}
