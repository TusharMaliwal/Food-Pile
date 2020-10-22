package common.requests.Funct;

import common.requests.Request;

public class AddItems extends Request{
    private String username;
    private String productID;
    private int quantity;

    public AddItems(String usename,String productID,int quantity){
        this.productID=productID;
        this.quantity=quantity;
        this.username=usename;
    }
    public String getProductID() {
        return productID;
    }
    public int getQuantity() {
        return quantity;
    }
    public String getUsername() {
        return username;
    }

}