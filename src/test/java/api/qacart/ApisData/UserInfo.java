package api.qacart.ApisData;

import api.qacart.apisEndPoint.UserApi;
import api.qacart.models.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;

public class UserInfo {

    public static User UserGenerate (){
        // we use Faker library Java to generate fake random data instead of using static data
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = "Test1234";// there's method for generating random password, but we will use static data

        return new User(email,password ,firstName, lastName);
    }

        public static User getRegisteredUser(){
          User user = UserGenerate();
          UserApi.Register(user);
          return user;
         }

         // this method will make register for new user and will return only the access_token from response
         public static String getUserToken(){
             User user = UserGenerate();
             Response response = UserApi.Register(user);
             return response.body().path("access_token");
         }



}
