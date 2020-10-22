package common.responses.Funct;

import common.responses.Response;
import common.responses.ResponseCode;

import java.io.Serializable;
import java.util.ArrayList;

public class DisplayPriceResponse extends Response  {
    private ArrayList<String> Price;

    public DisplayPriceResponse(ResponseCode code, String message, ArrayList<String> Price) {
        super(code, message);
        this.Price=Price;
    }
    public DisplayPriceResponse(ResponseCode code, String message){
        super(code, message);
    }

    public ArrayList<String> getPrice() {
        return Price;
    }
    @Override
    public String toString() {
        return String.format("Code is :"+code+" Message is "+message+" Items in the inventory Sorted with respect to Price are"+Price);
    }
    
}
