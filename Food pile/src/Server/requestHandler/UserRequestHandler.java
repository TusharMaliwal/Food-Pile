package Server.requestHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import common.requests.Charts.BarChart;
import common.requests.auth.SignupRequest;
import common.responses.Charts.BarChartResponse;
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

        public static SignupResponse signup(SignupRequest req, Connection connection) {

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
                        return new SignupResponse(FAILURE, "User already exists", req.getUsername());
                }
        }

        public static LoginResponse login(LoginRequest req, Connection connection) {

                String username = req.getUsername();
                String password = req.getPassword();
                String query = "SELECT * FROM Client WHERE username ='"+username+"' AND password = '"+password+"'";
                PreparedStatement preStat;
                ResultSet result;
                try {
                        preStat = connection.prepareStatement(query);
                        result = preStat.executeQuery();
                        while(result.next()) {
                                if (username.equals(result.getString("username")) && password.equals(result.getString("password"))) {
                                        return new LoginResponse(SUCCESS, "Welcome", req.getUsername());
                                } else {
                                        return new LoginResponse(DENIED, " Either the username or password is wrong ", req.getUsername());
                                }
                        }
                }catch(Exception e){
                        return new LoginResponse(SERVERDOWN,"Could not connect", req.getUsername());
                }

                return new LoginResponse(DENIED,"Invalid Credentials", req.getUsername());
        }

        public static AddItemsResponse add (AddItems req,Connection connection) {

                        String query = "INSERT INTO Inventory VALUES (?, ?, ?)";
                        try {
                                PreparedStatement preStat = connection.prepareStatement(query);
                                preStat.setString(1,req.getUsername());
                                preStat.setString(2, req.getProductID());
                                preStat.setInt(3,req.getQuantity());
               			        preStat.executeUpdate();
                		        return new AddItemsResponse(SUCCESS, "Items successfully Added.");
             		    }catch (SQLException e) {
                		        return new AddItemsResponse(FAILURE, "No such productID available.");
            		    }
        }

        public static UpdateItemsResponse update (UpdateItems req,Connection connection) {

                    String query = "update Inventory Set quantity = '" + req.getQuantity() +
                            "' where productID = '" + req.getProductID() + "' and username = '"+req.getUsername()+"'";
		        try {
		                PreparedStatement preStat = connection.prepareStatement(query);
		                preStat.executeUpdate();
                		return new UpdateItemsResponse(SUCCESS, "Item successfully Updated.");
		        }catch (SQLException e) {
		                return new UpdateItemsResponse(FAILURE, "Cannot find item.");
		        }
        }

        public static DeleteItemResponse delete (DeleteItem req,Connection connection) {

                String query = "Delete from Inventory WHERE username ='"
                        +req.getUsername()+"' AND productID = '"+req.getProductID()+"'";
	        try {
	                PreparedStatement preStat = connection.prepareStatement(query);
	                preStat.executeUpdate();
	                return new DeleteItemResponse(SUCCESS, "Items successfully Deleted.");
	        }catch (SQLException e) {
	                return new DeleteItemResponse(FAILURE, "Cannot find item.");
	        }
        }

        public static SearchNameResponse search_name
                (SearchName req,Connection connection)  {

                String query = "Select * from product where name = '"+req.getname()+ "'";
		try {
                        PreparedStatement preStat = connection.prepareStatement(query);
                        ResultSet result = preStat.executeQuery();
                        ArrayList<String> out= new ArrayList<>();
                        while (result.next()) {
                                out.add(" item name : " + req.getname() + " and category : "
                                        + result.getString("category") + " threshold is "
                                        +result.getInt("threshold")+ " Price is "
                                        +result.getInt("price")+" and productID is "
                                        +result.getString("productID")+"\n");
                        }
                        return new SearchNameResponse(SUCCESS, "Here are Your Results.", out);
                } catch (SQLException e) {
                        return new SearchNameResponse(FAILURE, "Cannot find item.");
                }
        }

          public static SearchCategoryResponse search_category
		(SearchCategory req,Connection connection) {
                try {
                        String query = "Select * from product where category = '"+req.getCategory()+"'";
                        ArrayList<String> out= new ArrayList<>();
		                PreparedStatement preStat = connection.prepareStatement(query);
		                ResultSet result = preStat.executeQuery();
                        while (result.next()) {
                                out.add(" item name : " + result.getString("name") + " and category : "
                                        + result.getString("category") + "threshold is "
                                        +result.getInt("threshold")+ "Price is "
                                        +result.getInt("price")+" and productID is "
                                        +result.getString("productID")+"\n");
                        }
                        return new SearchCategoryResponse(SUCCESS, "Here are Your Results.", out);
                } catch (SQLException e) {
                        return new SearchCategoryResponse(FAILURE, "Invalid");
                }

        }

        public static DisplayPriceResponse display_price
                (DisplayPrice req,Connection connection) throws SQLException {

                String query = "Select * from inventory where username = '"+req.getUsername()+"'";
                //selecting all owned by user 
		        ArrayList<String> out= new ArrayList<>();
                try {
                        PreparedStatement preStat = connection.prepareStatement(query);
                        ResultSet result = preStat.executeQuery();
                        while (result.next()) {// traversing the inventory table through each productID owned by user
                                String query1 = "Select * from product where productID = "+result.getString("productID")+"";// and selecting the productID
                                PreparedStatement preStat2 = connection.prepareStatement(query1);
                                ResultSet result2 = preStat2.executeQuery();
                                while (result2.next()) {// traversing product table to get price corresponding each productID
                                        //adding details if item in product table and inventory table is same
                                        out.add(result2.getInt("price") + ", is the Product price and Product name is "
                                                + result2.getString("name") + " Product Category is "
                                                + result2.getString("category")+" productID is "
                                                +result2.getString("productID")+"\n");

                                }
                        }
                        Collections.sort(out, new Comparator<String>() {//for sorting with respect to price
                                @Override
                                public int compare(String s1, String s2) {
                                        int s1int = Integer.parseInt(s1.substring(0, s1.indexOf(",")));
                                        int s2int = Integer.parseInt(s2.substring(0, s2.indexOf(",")));
                                        return s1int - s2int;
                                }
                        });

                        return new DisplayPriceResponse(SUCCESS, "Here are your results.", out);
                }catch (SQLException e) {
                       return new DisplayPriceResponse(FAILURE, "Invalid ");
                }
        }
         public static DisplayQuantResponse display_quant (DisplayQuant req,Connection connection) {

                String query = "Select * from inventory order by quantity DESC ";
		        ArrayList<String> out= new ArrayList<>();//query from sorting by quantity
                try {
                        PreparedStatement preStat = connection.prepareStatement(query);
                        ResultSet result = preStat.executeQuery();
                        while (result.next()) {// traversing the inventory table through each productID owned by user
                                if(result.getString("username").equals(req.getUsername())) {
                                        //printing details if username is same
                                        out.add("Product ID is " + result.getString("productID") + " Quantity of this is "
                                                + result.getInt("quantity")+"\n");
                                }
                        }
                        return new DisplayQuantResponse(SUCCESS, "Here are your results.", out);
                } catch (SQLException e) {
                        return new DisplayQuantResponse(FAILURE, "Invalid ");
                }
                
        }

        public static CheckAlertResponse check_alert
            (CheckAlert req,Connection connection)  {

                String query = "Select * from inventory where username = '"+req.getUsername()+"'";
                ArrayList<String>out= new ArrayList<>();
                try {
                        PreparedStatement preStat = connection.prepareStatement(query);
                        ResultSet result = preStat.executeQuery();
                        while (result.next()) {
                                String query2 = "Select * from product where productID = '"
                                                + result.getString("productID") + "'";
                                PreparedStatement preStat2 = connection.prepareStatement(query2);
                                ResultSet result2 = preStat2.executeQuery();
                                while (result2.next()) {
                                        if (result.getInt("quantity") < result2.getInt("threshold")) {
                                                out.add("Item with name :" + result2.getString("name")
                                                                + " and category :" + result2.getString("category")
                                                                + " Quantity is " + result.getInt("quantity")
                                                                + " is less than its threshold which is "
                                                                + result2.getInt("threshold")+"\n");
                                        }
                                }
                        }
                        return new CheckAlertResponse(ALERT, "Found Item:", out);
                } catch (SQLException e) {
                        return new CheckAlertResponse(FAILURE, "Cannot access Items");
                }
            }

        public static BarChartResponse barChart_display(BarChart req, Connection connection){
                String query = "Select * from inventory where username = '"+req.getUsername()+"'";
                try {
                        PreparedStatement preStat = connection.prepareStatement(query);
                        ArrayList<BarChartResponse>out= new ArrayList<>();
                        ResultSet result = preStat.executeQuery();
                        while (result.next()) {
                                String query2 = "Select * from product where productID = '"
                                        + result.getString("productID") + "'";
                                PreparedStatement preStat2 = connection.prepareStatement(query2);
                                ResultSet result2 = preStat2.executeQuery();
                                while (result2.next()) {
                                        BarChartResponse bChartResponse = new BarChartResponse(SUCCESS,"Result is ",result2.getString("name"),result2.getInt("price"),
                                                result2.getInt("threshold"),result.getInt("quantity"));
                                        out.add(bChartResponse);
                                }
                        }
                        return new BarChartResponse(ALERT, "Found Item:", out);
                } catch (SQLException e) {
                        return new BarChartResponse(FAILURE, "Cannot access Items");
                }
        }


}