package common.responses.auth;

import common.responses.Response;
import common.responses.ResponseCode;

public class LoginResponse extends Response {
    private String username;
    public LoginResponse(ResponseCode code, String message){
        super(code,message);
    }
    public LoginResponse(ResponseCode code, String message, String username) {
        super(code, message);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "userId='" + username + '\'' +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
