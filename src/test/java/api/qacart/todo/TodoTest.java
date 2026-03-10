package api.qacart.todo;

import api.qacart.ApisData.ErrorMessage;
import api.qacart.ApisData.TodoInfo;
import api.qacart.ApisData.UserInfo;
import api.qacart.apisEndPoint.TodoApi;
import api.qacart.models.ErrorMessages;
import api.qacart.models.Todo;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.groovy.control.messages.Message;
import org.testng.annotations.Test;
import java.io.File;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.testng.TestRunner.PriorityWeight.priority;


@Feature("Todo Feature") // annotation from allure to view all of these test cases in one feature at it's result report
public class TodoTest {
    //    the below  lines if we're needing to put json data of api as file
//    File AddTodo = new File("src/test/resources/TodoInfo.json");
//    File FakeAddTodo = new File("src/test/resources/FakeTodoInfo.json");
// File UpdateTodo = new File("src/test/resources/TodoInfo.json");
//    instead of using token value as hard coded , we can create an object store the returned value of token automatic from userinfo class by "getUserToken" method
    Todo todo = TodoInfo.GenerateTodoInfo();
    String token = UserInfo.getUserToken();
    String TaskID = TodoInfo.GetTodoID(todo, token);

    @Story("Should Be Able To Add Todo") // annotation from allure for each test case stored in the report as story
    @Test(priority = 1 , description = "Should Be Able To Add Todo") // we use the description for only made the allure report test case names more readable
    public void ShouldBeAbleToAddTodo() {

        // the below line will receive the attributes of todoValues "item and iscompleted " from Todoinfo class from its method GenerateTodoInfo
        // the below line will receive the endpoint response from todoapi class by addtodo method
        Response response = TodoApi.AddTodo(todo, token);

        Todo todoInfo = response.body().as(Todo.class);

        assertThat(response.statusCode(), equalTo(201));
        assertThat(todoInfo.getItem(), equalTo(todo.getItem()));
    }
    @Story("Should Not Be Able To Add Todo IF Is Completed Empty")
    @Test(priority = 2 , description = "Should Not Be Able To Add Todo IF Is Completed Empty")
    public void ShouldNotBeAbleToAddTodoIFIsCompletedEmpty() {
        Todo todo = new Todo("Learn English");

// the below line will receive the endpoint response from todoapi class by addtodo method
        Response response = TodoApi.AddTodo(todo, token);

        ErrorMessages ReturnedErrorMessage = response.body().as(ErrorMessages.class);

        assertThat(response.statusCode(), equalTo(400));
        assertThat(ReturnedErrorMessage.getMessage(), equalTo(ErrorMessage.IS_COMPLETED_REQUIRED));
    }
    @Story("Should Be Able To Get Todo By ID")
    @Test(priority = 3 , description = "Should Be Able To Get Todo By ID")
    public void ShouldBeAbleToGetTodoByID() {

        // the below line will receive the endpoint response from todoapi class by gettodo method
        Response response = TodoApi.GetTodo(token, TaskID);

        Todo todoInfo = response.body().as(Todo.class);
        assertThat(response.statusCode(), equalTo(200));
        assertThat(todoInfo.getItem(), equalTo(todo.getItem()));
        assertThat(todoInfo.getIsCompleted(), (equalTo(false)));

    }
    @Story("Should Be Able To Update Todo")
    @Test(priority = 4 , description = "Should Be Able To Update Todo")
    public void ShouldBeAbleToUpdateTodo() {



// the below line will receive the endpoint response from todoapi class by updatetodo method
        Response response = TodoApi.UpdateTodo(todo, token, TaskID);

// deseralization by take the json response and put it in object from todoClass
        Todo todoInfo = response.body().as(Todo.class);

        assertThat(response.statusCode(), equalTo(200));
        assertThat(todoInfo.getItem(), equalTo(todo.getItem()));
        assertThat(todoInfo.getIsCompleted(), equalTo(todo.getIsCompleted()));
    }
    @Story("Should Be Able To Delete Todo")
    @Test(priority = 5 , description = "Should Be Able To Delete Todo")
    public void ShouldBeAbleToDeleteTodo() {

// the below line will receive the endpoint response from todoapi class by deletetodo method
        Response response = TodoApi.DeleteTodo(token, TaskID);

        Todo todoInfo = response.body().as(Todo.class);

        assertThat(response.statusCode(), equalTo(200));
        assertThat(todoInfo.getItem(), equalTo(todo.getItem()));
        assertThat(todoInfo.getIsCompleted(), (equalTo(todo.getIsCompleted())));
    }
    @Story("Should Not Be Able To Get Todo After Deleted")
    @Test(priority = 6, description = "Should Not Be Able To Get Todo After Deleted")
    public void ShouldNotBeAbleToGetTodoAfterDeleted() {

        // the below line will receive the endpoint response from todoapi class by deletetodo method
        Response response = TodoApi.GetTodo(token, TaskID);

        ErrorMessages ReturnedErrorMessage = response.body().as(ErrorMessages.class);

        assertThat(response.statusCode(), equalTo(404));
        assertThat(ReturnedErrorMessage.getMessage(), equalTo(ErrorMessage.TODO_NOT_EXISTS));

    }
}
