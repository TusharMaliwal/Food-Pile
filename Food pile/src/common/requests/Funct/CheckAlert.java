package common.requests.Funct;

import common.requests.Request;

public class CheckAlert extends Request{
    private String username;

    public CheckAlert(String username){
        this.username=username;
    }
    public String getUsername() {
        return username;
    }

}
