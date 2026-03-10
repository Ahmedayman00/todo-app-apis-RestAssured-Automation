package api.qacart.ApisData;

import api.qacart.apisEndPoint.TodoApi;
import api.qacart.models.Todo;
import com.github.javafaker.Faker;
import io.restassured.response.Response;

public class TodoInfo {

    public static Todo GenerateTodoInfo() {
        Faker faker = new Faker();
        String item = faker.book().title();
        boolean isCompleted = false;

        return new Todo(item, isCompleted);
    }

    public static String GetTodoID(Todo todo, String token) {
        Response response = TodoApi.AddTodo(todo, token);
        return response.body().path("_id");
    }

}
