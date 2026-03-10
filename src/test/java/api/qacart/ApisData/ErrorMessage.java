package api.qacart.ApisData;

public class ErrorMessage {
    // instead of using the value of error message value as hard coded , we declare variables from each message and call it in each test case related to it's value
    public static final String EMAIL_IS_AlREADY_EXISTS = "Email is already exists in the Database";
    public static final String PASSWORD_IS_WRONG = "Please Fill a correct Password";
    public static final String EMAIL_IS_WRONG = "We could not find the email in the database";
    public static final String IS_COMPLETED_REQUIRED = "\"isCompleted\" is required";
    public static final String TODO_NOT_EXISTS = "We could not find the task in our database";

}
