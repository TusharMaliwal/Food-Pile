package common.requests.auth;


import common.requests.Request;

public class LoginRequest extends Request {
    private String username, password;
    public LoginRequest(String username, String password){
        this.username = username;
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }

}
