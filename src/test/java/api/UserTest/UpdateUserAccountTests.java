package api.UserTest;

import api.components.CreateResponse;
import api.components.JsonParser;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static api.components.Routes.putUserAccount_url;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class UpdateUserAccountTests {

    private static Response response;


    @BeforeAll
    public static void inti(){
            Map<String, String> item = JsonParser.createMap("src/test/resources/user.json");
            item.put("name", "Jeff Winger");
        response = CreateResponse.put(putUserAccount_url, item);
    }

    @Test
    @DisplayName("Test The Response Message")
    void testTheResponseMessage() {
        assertThat(response.jsonPath().getString("message"), equalTo("User updated!"));
    }

    @Test
    @DisplayName("Test The ResponseCode")
    void testTheResponseCode() {
        assertThat(response.jsonPath().getInt("responseCode"), equalTo(200));
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
