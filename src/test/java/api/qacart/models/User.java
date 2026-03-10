package api.qacart.models;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL) // we're using this annotation at class level because if there is any object at the class not using it in any case this annotation will exclude it
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String userID;
    @JsonProperty ("access_token") // if there's attribute's name has special characters or numbers we're using @JsonProperty annotation from jackson, and we put the attribute as argument in this annotation to able create variable from attribute without using special characters or numbers
    private String accessToken;
    private String password;

    // the default empty constructor
    public User() {
    }
    // the constructor which receive the values of attributes from "UserTest class" Register test cases
    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // the constructor which receive the values of attributes from "UserTest class" login test cases
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @JsonProperty ("access_token")
    public String getAccessToken() {
        return accessToken;
    }
    @JsonProperty ("access_token")
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
