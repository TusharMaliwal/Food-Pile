package common.requests.Funct;

import common.requests.Request;

public class DeleteItem extends Request {
    private String username;
    private String productID;
    
    public DeleteItem(String usename,String productID){
        this.username=username;
        this.productID=productID;
    }
    public String getProductID() {
        return productID;
    }
    public String getUsername() {
        return username;
    }
}
