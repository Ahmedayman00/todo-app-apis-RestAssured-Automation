package api.qacart.apisEndPoint;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseApi {
    /* //we can create method if we have many environment needing to run each of them " local , staging , production "

    public static String GetEnviroment() {
        String env = System.getenv("ENVIRONMENT");
        String BaseURL;
        switch (env) {
            case "PRODUCTION":
                BaseURL = "https://todoqacart.com";
                break;
            case "LOCAL":
                BaseURL = "http://localhost:8080";
                break;
            default:
                throw new RuntimeException("Invalid environment");

        }
        return BaseURL;
    } */

    public static RequestSpecification GetRequestSpec (){

        return given()
                .baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON);
    }
}
