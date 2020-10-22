package common.responses.Funct;

import common.responses.Response;
import common.responses.ResponseCode;

import java.util.ArrayList;

public class SearchNameResponse extends Response {
    private ArrayList<String> Name;

    public SearchNameResponse(ResponseCode code, String message, ArrayList<String> Name) {
        super(code, message);
        this.Name=Name;
    }

    public SearchNameResponse(ResponseCode code, String message) {
        super(code, message);
	}

	public ArrayList<String> getName() {
        return Name;
    }
    @Override
    public String toString() {
        return String.format("Code is :"+code+" Message is "+message+" Search results for the  Name are"+Name);
    }
    
}
