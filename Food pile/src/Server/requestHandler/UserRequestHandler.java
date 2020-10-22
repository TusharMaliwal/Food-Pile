package Server.requestHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.io.IOException;

import common.requests.auth.SignupRequest;
import common.responses.Funct.AddItemsResponse;
import common.responses.Funct.CheckAlertResponse;
import common.responses.Funct.DeleteItemResponse;
import common.responses.Funct.DisplayPriceResponse;
import common.responses.Funct.DisplayQuantResponse;
import common.responses.Funct.SearchCategoryResponse;
import common.responses.Funct.SearchNameResponse;
import common.responses.Funct.UpdateItemsResponse;
import common.responses.auth.LoginResponse;
import common.responses.auth.SignupResponse;
import common.requests.Funct.AddItems;
import common.requests.Funct.CheckAlert;
import common.requests.Funct.DeleteItem;
import common.requests.Funct.DisplayPrice;
import common.requests.Funct.DisplayQuant;
import common.requests.Funct.SearchCategory;
import common.requests.Funct.SearchName;
import common.requests.Funct.UpdateItems;
import common.requests.auth.LoginRequest;
import static common.responses.ResponseCode.*;

public class UserRequestHandler {

        public static SignupResponse signup(SignupRequest req, Connection connection)
                        throws IOException, ClassNotFoundException {

                String query = "INSERT INTO CLIENT VALUES (?,?,?,?)";
                PreparedStatement preStat;
                try {
                        preStat = connection.prepareStatement(query);
                        preStat.setString(1, req.getFname());
                        preStat.setString(2, req.getLname());
                        preStat.setString(3, req.getUsername());
                        preStat.setString(4, req.getPassword());
                        preStat.executeUpdate();
                        return new SignupResponse(SUCCESS, "Welcome to Food Pile", req.getUsername());
                } catch (SQLException e) {
                        return new SignupResponse(FAILURE, "User already exists");
                }
        }

        public static LoginResponse login(LoginRequest req, Connection connection)
                        throws IOException, ClassNotFoundException {

                String username = req.getUsername();
                String password = req.getPassword();
                String query = "SELECT * FROM Client WHERE username ='"+username+"' AND password = '"+password+"'";
                PreparedStatement preStat;
                ResultSet result;
                try {
                        preStat = connection.prepareStatement(query);
                        result = preStat.executeQuery();
                        if (username == result.getString("username")) {
                                return new LoginResponse(SUCCESS, "Welcome", req.getUsername());
                        } else {
                                return new LoginResponse(DENIED, "Invalid Credentials");
                        }
                }catch(SQLException e){
                        e.printStackTrace();
                }
                return null;
        }

        public static AddItemsResponse add
                    (AddItems req,Connection connection) throws IOException,ClassNotFoundException {

                        String query = "INSERT INTO Inventory VALUES (?, ?, ?)";
                        try {
                                PreparedStatement preStat = connection.prepareStatement(query);
                                preStat.setString(1,req.getUsername());
                                preStat.setString(2, req.getProductID());
                                preStat.setInt(3,req.getQuantity());
               			preStat.executeUpdate();
                		return new AddItemsResponse(SUCCESS, "Items successfully Added.");
             		}catch (SQLException e) {
                		return new AddItemsResponse(FAILURE, "Cannot Add items.");
            		}
                    }

        public static UpdateItemsResponse update
                (UpdateItems req,Connection connection) throws IOException,ClassNotFoundException {

                    String query = "update Inventory Set quantity='" + req.getQuantity() + "' where productID='" + req.getProductID() + 
                    "',username ='"+req.getUsername()+'"';
		   try {
                                PreparedStatement preStat = connection.prepareStatement(query);
               		        preStat.executeUpdate();
                		return new UpdateItemsResponse(SUCCESS, "Items successfully Updated.");
             	        }catch (SQLException e) {
                		return new UpdateItemsResponse(FAILURE, "Cannot find item.");
            		}
                }

        public static DeleteItemResponse delete
        (DeleteItem req,Connection connection) throws IOException,ClassNotFoundException{

                String query = "Delete from Inventory where productID ='" + req.getProductID() + "'and username ='"+req.getUsername()+ "'";
	        try {
                        PreparedStatement preStat = connection.prepareStatement(query);
               	        preStat.executeUpdate();
                        return new DeleteItemResponse(SUCCESS, "Items successfully Deleted.");
                }catch (SQLException e) {
                	return new DeleteItemResponse(FAILURE, "Cannot find item.");
            	}
        }

        public static SearchNameResponse search_name
                (SearchName req,Connection connection) throws IOException,ClassNotFoundException {

                String query = "Select * from product where name ='"+req.getname()+ "'";
		try {
                        PreparedStatement preStat = connection.prepareStatement(query);
                        ResultSet result = preStat.executeQuery();
                        ArrayList<String> out=new ArrayList<String>();
                        while (result.next()) {
                                String productID = result.getString("productID");
                                String query2 = "Select * from inventory where productID ='" + productID
                                                + "' and username='" + req.getUsername() + "'";
                                PreparedStatement preStat2 = connection.prepareStatement(query2);
                                ResultSet result2 = preStat2.executeQuery();
                                while (result2.next()) {
                                        out.add("quantity of item  with name : " + req.getname() + " and category : "
                                                        + result.getString("category") + "is "
                                                        + result2.getInt("quantity"));
                                }
                                return new SearchNameResponse(SUCCESS, "Here are Your Results.", out);
                        }
                } catch (SQLException e) {
                        return new SearchNameResponse(FAILURE, "Cannot find item.");
                }
                return null;
                }

          public static SearchCategoryResponse search_category
		(SearchCategory req,Connection connection) throws IOException,ClassNotFoundException, SQLException {

                String query = "Select * from product where category ='"+req.getCategory()+"'";
                ArrayList<String> out=new ArrayList<String>();
		PreparedStatement preStat = connection.prepareStatement(query);
		ResultSet result = preStat.executeQuery();
                        try {
                        while (result.next()) {
                                String productID = result.getString("productID");
                                String query2 = "Select * from inventory where productID ='" + productID
                                                + "' and username='" + req.getUsername() + "'";
                                preStat = connection.prepareStatement(query2);
                                ResultSet result2 = preStat.executeQuery();
                                while (result2.next()) {
                                        out.add("quantity of item with name : " + result.getString("name")
                                                        + " and category : " + req.getCategory() + " is "
                                                        + result2.getInt("quantity"));
                                }
                                return new SearchCategoryResponse(SUCCESS, "Here are Your Results.", out);
                        }
                } catch (SQLException e) {
                        return new SearchCategoryResponse(FAILURE, "Invalid");
                }
                return null;
        }

        public static DisplayPriceResponse display_price
                (DisplayPrice req,Connection connection) throws IOException,ClassNotFoundException, SQLException {

                String query = "Select * from inventory where username ='"+req.getUsername()+"'";
                //selecting all owned by user 
		ArrayList<String> out=new ArrayList<String>();
                PreparedStatement preStat = connection.prepareStatement(query);
                ResultSet result = preStat.executeQuery();
                try {
                        while (result.next()) {// traversing the inventory table through each productID owned by user
                                String query1 = "Select * from product order by price DESC  where productID ='"// sorting by price
                                                + result.getString("productID") + "'";// and selecting the productID
                                PreparedStatement preStat2 = connection.prepareStatement(query1);
                                ResultSet result2 = preStat2.executeQuery();
                                while (result2.next()) {// traversing product table to get price corrosponding each productID
                                        out.add("Product name is " + result2.getString("name") + " Product price is "
                                        + result2.getInt("price") + " Product Category is "+ result2.getString("category"));
                                }
                                return new DisplayPriceResponse(SUCCESS, "Here are your results.", out);
                        }
                }catch (SQLException e) {
                       return new DisplayPriceResponse(FAILURE, "Invalid ");
                }
                return null;
        }
         public static DisplayQuantResponse display_quant
		(DisplayQuant req,Connection connection) throws IOException,ClassNotFoundException {

                String query = "Select * from inventory order by quantity DSEC";
		ArrayList<String> out=new ArrayList<String>();//query from sorting by quantity
                try {
                        PreparedStatement preStat = connection.prepareStatement(query);
                        ResultSet result = preStat.executeQuery();
                        while (result.next()) {// traversing the inventory table through each productID owned by user
                                out.add("Product ID is " + result.getString("productID") + " Quantity of this is "
                                                + result.getInt("quantity"));
                        }
                        return new DisplayQuantResponse(SUCCESS, "Here are your results.", out);
                } catch (SQLException e) {
                        return new DisplayQuantResponse(FAILURE, "Invalid ");
                }
                
        }

        public static CheckAlertResponse check_alert
            (CheckAlert req,Connection connection) throws IOException,ClassNotFoundException  {

                String query = "Select * from inventory where username ='"+req.getUsername()+"'";
                ArrayList<String>out=new ArrayList<String>();
                try {
                        PreparedStatement preStat = connection.prepareStatement(query);
                        ResultSet result = preStat.executeQuery();
                        while (result.next()) {
                                String query2 = "Select * from product where productID ='"
                                                + result.getString("productID") + "'";
                                PreparedStatement preStat2 = connection.prepareStatement(query2);
                                ResultSet result2 = preStat2.executeQuery();
                                while (result2.next()) {
                                        if (result.getInt("quantity") < result2.getInt("threshold")) {
                                                out.add("Item with name :" + result2.getString("name")
                                                                + " and category :" + result2.getString("category")
                                                                + " Quantity is " + result.getInt("quantity")
                                                                + " is less than its threshold which is "
                                                                + result2.getInt("threshold"));
                                                return new CheckAlertResponse(ALERT, "Found Item:", out);
                                        }
                                }
                        }
                } catch (SQLException e) {
                        return new CheckAlertResponse(FAILURE, "Cannot access Items");
                }
                return null;
            }

}