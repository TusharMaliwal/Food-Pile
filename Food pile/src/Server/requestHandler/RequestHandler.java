package Server.requestHandler;

import common.requests.Request;
import common.requests.auth.LoginRequest;
import common.requests.auth.SignupRequest;
import common.requests.Funct.AddItems;
import common.requests.Funct.CheckAlert;
import common.requests.Funct.DeleteItem;
import common.requests.Funct.UpdateItems;
import common.requests.Funct.DisplayPrice;
import common.requests.Funct.DisplayQuant;
import common.requests.Funct.SearchName;
import common.requests.Funct.SearchCategory;
import common.responses.Response;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class RequestHandler {
    public static Response getResponse(Request req, Connection connection) throws SQLException,IOException,ClassNotFoundException {
        if(req instanceof SignupRequest){
            return UserRequestHandler.signup((SignupRequest) req,connection);
        }
        //Authentication requests
        else if(req instanceof LoginRequest){
            return UserRequestHandler.login((LoginRequest) req,connection);
        }
        else if(req instanceof AddItems){
            return UserRequestHandler.add((AddItems) req,connection);
        }
        //Groups requests
        else if(req instanceof UpdateItems){
            return UserRequestHandler.update((UpdateItems) req,connection);
        }
        else if(req instanceof DeleteItem){
            return UserRequestHandler.delete((DeleteItem)req,connection);
        }
        else if(req instanceof SearchName){
            return UserRequestHandler.search_name((SearchName) req,connection);
        }
        else if(req instanceof SearchCategory){
            return UserRequestHandler.search_category((SearchCategory) req,connection);
        }
        else if(req instanceof DisplayPrice){
            return UserRequestHandler.display_price((DisplayPrice) req,connection);
        }
        else if (req instanceof DisplayQuant){
            return UserRequestHandler.display_quant((DisplayQuant) req,connection);
        }
        else if (req instanceof CheckAlert){
            return UserRequestHandler.check_alert((CheckAlert) req,connection);
        }

        else{
            return null;
        }
    }
}
