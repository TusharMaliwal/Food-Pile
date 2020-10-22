package common.requests.Funct;

import common.requests.Request;

public class SearchName extends Request {
    private String username;
    private String name;

    public SearchName(String username,String name){
        this.username=username;
        this.name=name;
    }
    public String getname() {
        return name;
    }
    public String getUsername() {
        return username;
    }
    
}
