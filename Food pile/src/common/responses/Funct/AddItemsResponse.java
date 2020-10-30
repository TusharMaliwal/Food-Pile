package common.responses.Funct;

import common.responses.Response;
import common.responses.ResponseCode;

public class AddItemsResponse extends Response {
    public AddItemsResponse(ResponseCode code, String message){
        super(code,message);
    }

    @Override
    public String toString() {
        return "AddItemsResponse{code :"+code+" message :"+message;
    }
}
