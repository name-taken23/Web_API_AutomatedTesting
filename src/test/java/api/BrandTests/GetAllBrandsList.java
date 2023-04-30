package api.BrandTests;

import api.components.CreateResponse;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static api.components.Routes.getBrands_url;
import static org.apache.commons.lang3.time.DateUtils.parseDateStrictly;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetAllBrandsList {
    private static final Logger logger = LogManager.getLogger(GetAllBrandsList.class);
    private static Response response;
    private static ResponseSpecification responseSpecification;


    @BeforeAll
    public static void createResponseSpecification() {
        responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.HTML).build();
    }

    @BeforeAll
    public static void init() {
        response = CreateResponse.get(getBrands_url);
    }

    @Test
    @DisplayName("Check the status code is correct")
    void CheckTheStatusCodeIsCorrect() {
        response.then().assertThat().statusCode(200);
    }


    @Nested
    @Order(1)
    @DisplayName("Testing responseBody")
    class TestingResponseBody {

        @Test
        @DisplayName("Testing response body id and brand")
        public void testingResponseBodyIdAndBrand() {
            responseSpecification
                    .body("brands.brand", hasItems("Polo", "H&M", "Madame", "Mast & Harbour", "Babyhug", "Allen Solly Junior", "Kookie Kids", "Biba"))
                    .body("brands.id", hasItem(lessThan(45)));

        }


        @Test
        @DisplayName("Testing IDs and brands in the response")
        public void TestingIDsAndBrandsInTheResponse() {

            List<BrandsItem> brandsList = response.jsonPath().getList("brands", BrandsItem.class);

            // Asserting the ID and brand values for each item in the list
            for (BrandsItem item : brandsList) {
                assertEquals(item.getId(), response.jsonPath().getInt("brands.find { it.id == " + item.getId() + " }.id"));
                assertEquals(item.getBrand(), response.jsonPath().getString("brands.find { it.id == " + item.getId() + " }.brand"));
            }


        }


        @Test
        @DisplayName("Testing whether id 2 H&M brand ")
        void testingWhetherId2HAndMBrand() {
            responseSpecification.body("brands.find { it.id == 2 }.brand", equalTo("H&M"));
        }

        @Test
        @DisplayName("Testing whether id 3 Madame brand ")
        void testingWhetherId3MadameBrand() {
            responseSpecification.body("brands.find { it.id == 3 }.brand", equalTo("Madame"));
        }
    }


    @Nested
    @DisplayName("Testing Headers")
    class TestingHeaders {


        @Test
        @DisplayName("Test size of headers")
        void testSizeOfHeaders() {
            int sizeOfHeaders = response.getHeaders().size();
            MatcherAssert.assertThat(sizeOfHeaders, is(18));   //Hamcrest
        }


        @Test
        @DisplayName("Test the server header")
        void testTheServerHeader() {

            response.then().spec(responseSpecification).and().assertThat().header("Server", "cloudflare");

        }


        @Test
        @DisplayName("Test the content-type header")
        void testTheContentTypeHeader() {

            response.then().spec(responseSpecification).and().assertThat().header("Content-Type", "text/html; charset=utf-8");

        }

        @Test
        @DisplayName("Test the Transfer-Encoding header")
        void testTheTransferEncodingHeader() {

            response.then().spec(responseSpecification).and().assertThat().header("Transfer-Encoding", "chunked");

        }

        @Test
        @DisplayName("Test the Connection header")
        void testTheConnectionHeader() {

            response.then().spec(responseSpecification).and().assertThat().header("Connection", "keep-alive");

        }

        @Test
        @DisplayName("Test the vary header")
        void testTheVaryHeader() {

            response.then().spec(responseSpecification).and().assertThat().header("vary", "Accept,Cookie,Accept-Encoding");

        }

        @Test
        @DisplayName("Test the referrer-policy header")
        void testTheReferrerPolicyHeader() {

            response.then().spec(responseSpecification).and().assertThat().header("referrer-policy", "same-origin");

        }

        @Test
        @DisplayName("Test the x-frame-options header")
        void testTheX_frame_optionsHeader() {

            response.then().spec(responseSpecification).and().assertThat().header("x-frame-options", "DENY");

        }

        @Test
        @DisplayName("Test the x-content-type-options header")
        void testTheX_content_type_optionsHeader() {

            response.then().spec(responseSpecification).and().assertThat().header("x-content-type-options", "nosniff");

        }

        @Test
        @DisplayName("Test the allow header")
        void testTheAllowHeader() {

            response.then().spec(responseSpecification).and().assertThat().header("allow", "POST, PUT, OPTIONS, GET, DELETE");

        }

        @Test
        @DisplayName("Test the CF-Cache-Status header")
        void testTheCF_Cache_StatusHeader() {

            response.then().spec(responseSpecification).and().assertThat().header("CF-Cache-Status", "DYNAMIC");

        }


        @Test
        @DisplayName("Test the Date header")
        void testTheDateHeader() {
            try {
                response.then().spec(responseSpecification).and().assertThat().header(String.valueOf(parseDateStrictly("Date")), equalTo(new Date()));
            } catch (ParseException e) {
                logger.error(e.getMessage());
            }

        }
    }
}








