package api.qacart.apisEndPoint;


import api.qacart.ApisData.Route;
import api.qacart.models.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

// here we will declare the end point of register and login and return it to user test class
public class UserApi {

    public static Response Register (User user) {
       return  given()
                .spec(BaseApi.GetRequestSpec())
                .body(user)
                .when()
                .post(Route.Register_Route)
                .then()
                .log().all()
                .extract().response();
    }

    public static Response Login (User user) {

       return given()
                .spec(BaseApi.GetRequestSpec())
                .body(user)
                .when()
                .post(Route.Login_Route)
                .then()
                .log().all()
                .extract().response();
    }
}
