package pocketruck.penny.pocketruck_android.model;

public class SignUp {
    private String username;
    private String password;

    public  SignUp(){

    }

    public SignUp(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
