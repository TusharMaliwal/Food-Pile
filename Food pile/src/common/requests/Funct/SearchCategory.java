package common.requests.Funct;

import common.requests.Request;

public class SearchCategory extends Request {
    private String username;
    private String category;

    public SearchCategory(String username,String category){
        this.username=username;
        this.category=category;
    }
    public String getCategory() {
        return category;
    }
    public String getUsername() {
        return username;
    }
    
}
