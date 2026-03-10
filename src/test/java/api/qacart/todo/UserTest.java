package api.qacart.todo;

import api.qacart.ApisData.ErrorMessage;
import api.qacart.ApisData.UserInfo;
import api.qacart.apisEndPoint.UserApi;
import api.qacart.models.ErrorMessages;
import api.qacart.models.User;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.io.File;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;


@Feature("User Feature") // annotation from allure to view all of these test cases in one feature at it's result report
public class UserTest {
// the below there lines if we're needing to put json data of api as file
//     RegisterInfo = new File("src/test/resources/RegisterInfo.json");
//    File LoginInfo = new File("src/test/resources/LoginInfo.json");
//    File FakeLoginInfo = new File("src/test/resources/FakeLoginInfo.json");

    @Story("Should Be Able To Register") // annotation from allure for each test case stored in the report as story
    @Test (description = "Should Be Able To Register") // we use the description for only made the allure report test case names more readable
    public void ShouldBeAbleToRegister() {
//        User user = new User("cw.hamza777@gmail.com","Test1234" , "Ahmed" , "Hassan");
        // we use the below line to receive random user info from Userinfo class instead the above line of static data
        User user = UserInfo.UserGenerate();

        // the below line will receive the endpoint response from Userapi class by register method
        Response response = UserApi.Register(user);


// deserialization by take the json response and put it in object from userClass
        User ResponseUserData = response.body().as(User.class);   // we declare object 'ResponseUserData' from 'User' class and will store the response user data at User class by using as(User.class)

        assertThat(response.statusCode(), equalTo(201));
        assertThat(ResponseUserData.getFirstName(), equalTo(user.getFirstName()));
        assertThat(ResponseUserData.getAccessToken(), not(equalTo(null)));
    }
    @Story("Should Not Be Able To Register By Email Exists")
    @Test (description = "Should Not Be Able To Register By Email Exists")
    public void ShouldNotBeAbleToRegisterByEmailExists() {

        User user = UserInfo.getRegisteredUser();
        Response response = UserApi.Register(user);

        ErrorMessages ReturnedErrorMessage = response.body().as(ErrorMessages.class);

                 assertThat(response.statusCode(), equalTo(400));
                 assertThat(ReturnedErrorMessage.getMessage(), equalTo(ErrorMessage.EMAIL_IS_AlREADY_EXISTS));
         }
    @Story("Should Be Able To Login")
    @Test (description = "Should Be Able To Login")
    public void ShouldBeAbleToLogin() {
        User user = UserInfo.getRegisteredUser();
        User userData = new User(user.getEmail() , user.getPassword());

       Response response = UserApi.Login(userData); // we will use userData object to send "email and password" at login end point

        User ResponseUserData = response.body().as(User.class);   // we declare object 'ResponseUserData' from 'User' class and will store the response user data at User class by using as(User.class)

        assertThat(response.statusCode(), equalTo(200));
        assertThat(ResponseUserData.getFirstName(), equalTo(user.getFirstName()));
        assertThat(ResponseUserData.getAccessToken(), not(equalTo(null)));
    }
    @Story("Should Be Not Able To Login If User Password Not Correct Or Empty Password")
    @Test (description = "Should Be Not Able To Login If User Password Not Correct Or Empty Password")
    public void ShouldBeNotAbleToLoginIfUserPasswordNotCorrectOrEmptyPassword() {

        User user = UserInfo.getRegisteredUser();
        User userData = new User(user.getEmail() , "wrong");

        Response response = UserApi.Login(userData);

        ErrorMessages ReturnedErrorMessage = response.body().as(ErrorMessages.class);

        assertThat(response.statusCode(), equalTo(400));
        assertThat(ReturnedErrorMessage.getMessage(), equalTo(ErrorMessage.PASSWORD_IS_WRONG));
    }

    @Test (description = "Should Be Not Able To Login If User Email Not Correct")
    public void ShouldBeNotAbleToLoginIfUserEmailNotCorrect() {
        User user = UserInfo.getRegisteredUser();
        User userData = new User("ce@gmail.com" , user.getPassword());

        Response response = UserApi.Login(userData);

        ErrorMessages ReturnedErrorMessage = response.body().as(ErrorMessages.class);

        assertThat(response.statusCode(), equalTo(400));
        assertThat(ReturnedErrorMessage.getMessage(), equalTo(ErrorMessage.EMAIL_IS_WRONG));
    }

}
