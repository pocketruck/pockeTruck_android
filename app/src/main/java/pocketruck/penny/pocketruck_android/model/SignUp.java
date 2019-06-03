package pocketruck.penny.pocketruck_android.model;

public class SignUp {
    String username;
    String password;

    public SignUp(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void setPassword(String password) {
        this.password = password;
    }
}
