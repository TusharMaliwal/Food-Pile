package common.responses.Funct;

import common.responses.Response;
import common.responses.ResponseCode;

import java.util.ArrayList;

public class DisplayQuantResponse extends Response {
    private ArrayList<String> Quant;

    public DisplayQuantResponse(ResponseCode code, String message, ArrayList<String> Quant) {
        super(code, message);
        this.Quant=Quant;
    }
    public DisplayQuantResponse(ResponseCode code, String message){
        super(code, message);
    }

    public ArrayList<String> getQuant() {
        return Quant;
    }
    @Override
    public String toString() {
        return String.format("Code is :"+code+" Message is "+message+" Items in the inventory Sorted with respect to Quantity are"+Quant);
    }
    
}
