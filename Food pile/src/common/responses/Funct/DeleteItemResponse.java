package common.responses.Funct;

import common.responses.Response;
import common.responses.ResponseCode;

public class DeleteItemResponse extends Response {
    public DeleteItemResponse(ResponseCode code, String message){
        super(code,message);
    }
}
