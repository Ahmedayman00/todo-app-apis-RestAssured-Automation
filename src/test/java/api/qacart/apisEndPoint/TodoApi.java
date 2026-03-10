package api.qacart.apisEndPoint;

import api.qacart.ApisData.Route;
import api.qacart.models.Todo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class TodoApi {

    public static Response AddTodo(Todo todo , String token) {

       return given()
               .spec(BaseApi.GetRequestSpec())
                .body(todo)
                .auth().oauth2(token)
                .when()
                .post(Route.Todo_Route)
                .then()
                .log().all()
                .extract().response();

    }

   public static Response GetTodo(String token , String TaskID) {

       return  given()
               .spec(BaseApi.GetRequestSpec())
               .auth().oauth2(token)
               .when()
               .get(Route.Todo_Route + "/" +TaskID)
               .then()
               .log().all()
               .extract().response();
   }
    public static Response UpdateTodo(Todo todo ,String token , String TaskID) {

        return  given()
                .spec(BaseApi.GetRequestSpec())
                .body(todo)
                .auth().oauth2(token)
                .when()
                .put(Route.Todo_Route + "/" +TaskID)
                .then()
                .log().all()
                .extract().response();
    }

    public static Response DeleteTodo(String token , String TaskID) {

        return  given()
                .spec(BaseApi.GetRequestSpec())
                .auth().oauth2(token)
                .when()
                .delete(Route.Todo_Route + "/" +TaskID)
                .then()
                .log().all()
                .extract().response();
    }

}
