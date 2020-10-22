package common.responses;

import java.io.Serializable;

public abstract class Response implements Serializable {
    protected ResponseCode code;
    protected String message;

    public Response(ResponseCode code, String message){
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public ResponseCode getCode() {
        return code;
    }

    @Override
    public String toString() {
        return String.format("Response : code= "+code+" message= " + message + "");
    }
}
