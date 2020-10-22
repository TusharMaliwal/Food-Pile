package common.responses.auth;

import common.responses.Response;
import common.responses.ResponseCode;

public class SignupResponse extends Response {
    private String username;

    public SignupResponse(ResponseCode code, String message){
        super(code,message);
    }
    public SignupResponse(ResponseCode code, String message,String username){
        super(code,message);
        this.username=username;
    }


    @Override
    public String toString() {
        return "SignupResponse{" +
                "user='" + username + '\'' +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
