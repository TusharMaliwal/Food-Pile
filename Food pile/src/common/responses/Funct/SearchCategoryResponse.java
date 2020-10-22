package common.responses.Funct;

import common.responses.Response;
import common.responses.ResponseCode;

import java.util.ArrayList;

public class SearchCategoryResponse extends Response {
    private ArrayList<String> Categ;

    public SearchCategoryResponse(ResponseCode code, String message, ArrayList<String> Categ) {
        super(code, message);
        this.Categ=Categ;
    }
    public SearchCategoryResponse(ResponseCode code, String message){
        super(code, message);
    }

    public ArrayList<String> getCateg() {
        return Categ;
    }
    @Override
    public String toString() {
        return String.format("Code is :"+code+" Message is "+message+" Search results for the  Category are"+Categ);
    }
    
}
