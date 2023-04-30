package api.components;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

import java.util.Map;

public class CreateResponse {
  private static Response response;

  /*

    ~~POST REQUESTS~~

   */
  public static Response post(Map<String, String> map, String url){
    response=given().contentType("application/x-www-form-urlencoded").formParams(map).post(url);
    return response;
  }

  public static Response post(String formParam, String url){
    response=given().contentType("application/x-www-form-urlencoded").formParam(formParam).post(url);
    return response;
  }
  public static<T> Response post(String url, String field1, T email, String field2, T password){
    response = given().log().all().contentType("application/x-www-form-urlencoded")
        .formParams(field1, email, field2, password)
        .post(url);

    return response;

  }

  public static<T> Response post(String url, String item, T product){
    response = given().log().all().contentType("application/x-www-form-urlencoded")
        .formParams(item, product)
        .post(url);

    return response;

  }

  public static Response post(Map<String, String> map, String key1, String key2) {
    response = given()
        .contentType("application/x-www-form-urlencoded")
        .formParams(key1, map.get(key1), key2, map.get(key2))
        .post(Routes.postLoginDetails_url);
    return response;
  }

  public static Response post( String url, String item1, String item2, String item3, String item4){
    response = given()
        .contentType("application/x-www-form-urlencoded")
        .formParams(item1,item2,item3,item4)
        .post(url);
    return response;
  }

  public static Response post(String url, String item1, Object item2, Object item3, Object item4){
    response = given()
        .contentType("application/x-www-form-urlencoded")
        .formParams(item1,item2,item3,item4)
        .post(url);
    return response;
  }

   /*

    ~~GET REQUESTS~~

   */

  public static Response get(String url){
    response = given()
        .when()
        .get(url)
        .then().extract().response();
    return response;
  }
  public static Response get(String url, String query){
    response = given()
        .when()
        .get(url+query)
        .then().extract().response();
    return response;
  }

  /*

   ~~DELETE REQUESTS~~

  */
  public static Response delete(Map<String, String> map, String item1, String item2, String url){
    response=given().contentType("application/x-www-form-urlencoded").formParams(map.keySet().toArray()[1].toString(), map.get(item1), map.keySet().toArray()[2].toString(), map.get(item2)).delete(url);
    return response;
  }

  public static Response delete(String item, String url){
    response = given().contentType("application/x-www-form-urlencoded").formParam(item).delete(url);
    return response;
  }

 public static Response delete(String url){
   response = given().when().delete(url).then().extract().response();
   return response;
 }

 // PUT Request
// birth_date, birth_month, birth_year, firstname, lastname, company, address1, address2, country, zipcode, state, city, mobile_number
  public static Response put(String url,
                             Map<String, String> user){
    response = given()
            .contentType("application/x-www-form-urlencoded")
            .formParams(user)
            .put(url);
    return response;

  }
}
