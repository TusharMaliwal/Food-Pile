package common.responses.Funct;

import java.util.ArrayList;

import common.responses.Response;
import common.responses.ResponseCode;

public class CheckAlertResponse extends Response{
    private ArrayList<String> Result;

    public CheckAlertResponse(ResponseCode code, String message, ArrayList<String>Result) {
        super(code, message);
        this.Result=Result;
    }

    public CheckAlertResponse(ResponseCode code, String message) {
        super(code, message);
	}
    public ArrayList<String> getResult() {
        return Result;
    }
    @Override
    public String toString() {
        return String.format("Code is :"+code+" Message is "+message+" Search results for the Alert are"+Result);
    }
}
