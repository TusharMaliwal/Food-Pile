package common.requests.Funct;

import common.requests.Request;

public class DisplayQuant extends Request {
    private String username;

    public DisplayQuant(String username){
        this.username=username;
    }
    public String getUsername() {
        return username;
    }
}
