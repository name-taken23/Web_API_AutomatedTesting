package api.UserTest;

import static io.restassured.RestAssured.given;

import static org.hamcrest.MatcherAssert.assertThat;


import static org.hamcrest.Matchers.equalTo;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Map;
import api.components.CreateResponse;
import api.components.JsonParser;
import api.components.Routes;


@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class AccountCreateAndDeleteTest {

  static Response response;
  static Map<String, String> item;

  @BeforeAll
  static void init(){
    item = JsonParser.createMap("src/test/resources/user.json");
  }
  @Order(1)
  @Nested
  @TestClassOrder(ClassOrderer.OrderAnnotation.class)
  @DisplayName("DELETE user requests checks")
  class DeleteRequestChecks {

    @Order(1)
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Successful delete request checks")
    class SuccessfulDeleteRequestCheck {
      @BeforeAll
      void delete() {
        response = CreateResponse.delete(item, "email", "password", Routes.deleteUserAccount_url);
      }
      @Order(1)
      @Test
      @DisplayName("Check the response code")
      void checkTheResponseCode() {
        assertThat(response.jsonPath().getString("responseCode"), equalTo("200"));
      }

      @Order(2)
      @Test
      @DisplayName("Check the status code")
      void checkTheStatusCode() {
        assertThat(response.getStatusCode(), equalTo(200));
      }

      @Order(3)
      @Test
      @DisplayName("Check response body for success message")
      void checkResponseBodyForSuccessMesssage() {
        assertThat(response.body().jsonPath().getString("message"), equalTo("Account deleted!"));

      }
    }
    @Order(2)
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DisplayName("Unsuccessful delete request checks")
    class unSuccessfulDeleteRequestCheck {

      @BeforeAll
      void delete() {
        response = CreateResponse.delete("false", Routes.deleteUserAccount_url);
      }
      @Order(1)
      @Test
      @DisplayName("Check the response code - wrong data")
      void checkTheResponseCodeWrongData() {
        assertThat(response.jsonPath().getString("responseCode"), equalTo("400"));
      }
      @Order(2)
      @Test
      @DisplayName("Check the status code - wrong data")
      void checkTheStatusCodeWrongData() {
        assertThat(response.getStatusCode(), equalTo(200));
      }
      @Order(3)
      @Test
      @DisplayName("Check response body for  message - wrong data")
      void checkTheMessageBodyWrongData() {
        assertThat(response.body().jsonPath().getString("message"), equalTo("Bad request, email parameter is missing in DELETE request."));
      }
    }
  }
  @Order(2)
  @Nested
  @TestClassOrder(ClassOrderer.OrderAnnotation.class)
  @DisplayName("Create User POST Checks")
  class  PostRequestChecks {

    @Order(1)
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DisplayName("Successful create user request checks")
    class successfulCreateUserChecks {
      @BeforeAll
      void createUser() {
        response = CreateResponse.post(item, Routes.postUserAccount_url);
      }

      @Order(1)
      @Test
      @DisplayName("Check the response code")
      void checkTheStatusCode() {
        assertThat(response.jsonPath().getString("responseCode"), equalTo("201"));
      }

      @Order(2)
      @Test
      @DisplayName("Check the status code")
      void checkTheResponseCode() {
        assertThat(response.getStatusCode(), equalTo(200));
      }

      @Order(3)
      @Test
      @DisplayName("Check response body for success message")
      void checkResponseBodyForSuccessMesssage() {
        assertThat(response.body().jsonPath().getString("message"), equalTo("User created!"));
      }
    }

    @Order(2)
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DisplayName("Unsuccessful create user request checks")
    class unSuccessfulCreateUserChecks {
      @BeforeAll
      void createUserFalse() {
        response = CreateResponse.post("false", Routes.postUserAccount_url);
      }

      @Order(1)
      @Test
      @DisplayName("Check the response code - wrong data")
      void checkTheStatusCodeWrongData() {
        assertThat(response.jsonPath().getString("responseCode"), equalTo("400"));
      }

      @Order(2)
      @Test
      @DisplayName("Check the status code - wrong data")
      void checkTheResponseCodeWrongData() {
        assertThat(response.getStatusCode(), equalTo(200));
      }

      @Order(3)
      @Test
      @DisplayName("Check response body for message - wrong data")
      void checkResponseBodyForUnsuccessfulMesssage() {
        assertThat(response.body().jsonPath().getString("message"), equalTo("Bad request, name parameter is missing in POST request."));
      }
    }
  }
}
