package Client.main;

import common.requests.Request;
import common.requests.auth.LoginRequest;
import common.requests.auth.SignupRequest;
import common.responses.Response;
import common.responses.Funct.AddItemsResponse;
import common.responses.Funct.DeleteItemResponse;
import common.responses.Funct.DisplayPriceResponse;
import common.responses.Funct.DisplayQuantResponse;
import common.responses.Funct.SearchCategoryResponse;
import common.responses.Funct.SearchNameResponse;
import common.responses.Funct.UpdateItemsResponse;
import common.responses.auth.LoginResponse;
import common.responses.auth.SignupResponse;
import common.requests.Funct.AddItems;
import common.requests.Funct.DeleteItem;
import common.requests.Funct.DisplayPrice;
import common.requests.Funct.DisplayQuant;
import common.requests.Funct.SearchCategory;
import common.requests.Funct.SearchName;
import common.requests.Funct.UpdateItems;

import java.sql.SQLException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.net.Socket;
import java.io.ObjectInputStream;

public class Client {
    private Object res;

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

	    String username=null;
	    boolean isLoggedin = false;
        Socket socket = new Socket("localhost", 5400);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

	while(!isLoggedin){//for login/signup
        System.out.println("enter 1 to register otherwise proceed to login");
        int ch = Integer.parseInt(bufferedReader.readLine());
        switch(ch){
            case 1:{
                System.out.println("enter your first name");
                String fname=bufferedReader.readLine();
                System.out.println("enter your last name");
                String lname=bufferedReader.readLine();
                System.out.println("enter your username");
                username = bufferedReader.readLine();
                System.out.println("enter your password");
                String password=bufferedReader.readLine();
                Request req = new SignupRequest(fname,lname,username,password);
                new Client().sendRequest(socket,req);
                Response res = (SignupResponse) new Client().getResponse(socket);
                System.out.println(res);
                break;
            }
            
            case 2:{
                System.out.println("enter your registered username");
                username = bufferedReader.readLine();
                System.out.println("enter your password");
                String password=bufferedReader.readLine();
                Request req = new LoginRequest(username, password);
                new Client().sendRequest(socket, req);
                Response res = (LoginResponse) new Client().getResponse(socket);
                System.out.println(res);
                break;
            }
        }
    }

       /* new Thread(new Runnable() {// alert feature
            @Override
            public void run() {
                while (true) {
                    try {
                        new Client().check(socket, bufferedReader, username);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(out!=""){
                        System.out.println(out);

                    }
                }
            }
        });*/
        int choice;
        do {
             System.out.println("Enter the choice you want to make");
             System.out.println("Enter 1 to add items in the inventory");
             System.out.println("Enter 2 to update item Inventory in the inventory");
             System.out.println("Enter 3 to remove items from the inventory");
             System.out.println("Enter 4 to search items in the inventory");
             System.out.println("Enter 5 to sort and display items in the inventory");
             System.out.println("enter 6 to Quit");
             choice=Integer.parseInt(bufferedReader.readLine());;
                switch(choice){
                    case 1:
                    {
                        System.out.println("Enter the ProductID of the product");
                        String productID = bufferedReader.readLine();
                        System.out.println("Enter the quantity of the producrt");
                        int quantity=Integer.parseInt(bufferedReader.readLine());
                        Request req = new AddItems(username,productID,quantity);
                        new Client().sendRequest(socket, req);
                        Response res = (AddItemsResponse) new Client().getResponse(socket);
                        System.out.println(res);
                        break;
                    }
                    case 2:
                    {
                        System.out.println("Enter the ProductID of the product which needs to be updated");
                        String productID = bufferedReader.readLine();
                        System.out.println("Enter the new quantity of the item");
                        int quantity = Integer.parseInt(bufferedReader.readLine());
                        Request req = new UpdateItems(username, productID, quantity);
                        new Client().sendRequest(socket, req);
                        Response res = (UpdateItemsResponse) new Client().getResponse(socket);
                        System.out.println(res);
                        break;
                    }
                    case 3:
                    {
                        System.out.println("enter the productID of the product  to be deleted");
                        String productID = bufferedReader.readLine();
                        Request req = new DeleteItem(username, productID);
                        new Client().sendRequest(socket, req);
                        Response res = (DeleteItemResponse) new Client().getResponse(socket);
                        System.out.println(res);
                        break;
                    }
                    case 4:
                    {
                        System.out.println("For searching based on Name press 1 else for category based searching press 2");
                        int n = Integer.parseInt(bufferedReader.readLine());
                        if(n==1){
                            System.out.println("Enter the product name");
                            String name = bufferedReader.readLine();
                            Request req = new SearchName(username, name);
                            new Client().sendRequest(socket, req);
                            Response res = (SearchNameResponse) new Client().getResponse(socket);
                            System.out.println(res);
                        }
                        if(n!=2){
                            System.out.println("Enter the category name");
                            String category = bufferedReader.readLine();
                            Request req = new SearchCategory(username, category);
                            new Client().sendRequest(socket, req);
                            Response res = (SearchCategoryResponse) new Client().getResponse(socket);
                            System.out.println(res);
                        }
                        break;
                    }
                    case 5:
                    {
                        System.out.println("For sorting and displaying the inventory with respect to price press 1 otherwise press 2");//for price priceORquant=1
                        int priceORquant = Integer.parseInt(bufferedReader.readLine());
                        if(priceORquant==1){
                            Request req = new DisplayPrice(username);
                            new Client().sendRequest(socket, req);
                            Response res = (DisplayPriceResponse) new Client().getResponse(socket);
                            System.out.println(res);
                        }
                        else{
                            Request req = new DisplayQuant(username);
                            new Client().sendRequest(socket, req);
                            Response res = (DisplayQuantResponse) new Client().getResponse(socket);
                            System.out.println(res);
                        }
                        break;
                    }
                    case 6:
                    {
                        System.out.println("EXIT POINT\n");
                        break;
                    }
                    default:
                    {
                        System.out.println("please enter a valid choice(1/2/3/4/5/6)\n");
                    }
                 }
            }
            while(choice!=6);
        }

        public void check
            (Socket socket, BufferedReader bufferedReader,String username) throws IOException,ClassNotFoundException  {

            }

            public Object getResponse(Socket socket) throws IOException,ClassNotFoundException{
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                new Thread(new Runnable() {//thread for recieving objects
                    @Override
                    public void run() {
                        
                        while (true) {
                            try {
                              res = objectInputStream.readObject();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();//input Stream thread
                return res;
            }

            public void sendRequest(Socket socket,Request req) throws IOException,ClassNotFoundException{
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(req);
                objectOutputStream.flush();
            }

}