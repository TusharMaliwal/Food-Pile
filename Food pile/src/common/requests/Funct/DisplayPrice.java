package common.requests.Funct;

import common.requests.Request;

public class DisplayPrice extends Request {
    private String username;

   public DisplayPrice(String username){
        this.username=username;
    }
    public String getUsername() {
        return username;
    }

}
