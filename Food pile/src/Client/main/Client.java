package Client.main;

import common.requests.Funct.*;
import common.requests.Request;
import common.requests.auth.LoginRequest;
import common.requests.auth.SignupRequest;
import common.responses.Funct.*;
import common.responses.Response;
import common.responses.ResponseCode;
import common.responses.auth.LoginResponse;
import common.responses.auth.SignupResponse;


import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.net.Socket;
import java.io.ObjectInputStream;

import static java.lang.Thread.sleep;

public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        String username = null;
        boolean isLogin = false;
        Socket socket = new Socket("localhost", 5470);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

        while (!isLogin) {// for login/signup

            System.out.println("enter 1 to register otherwise proceed to login");
            int ch = Integer.parseInt(bufferedReader.readLine());
            if (ch == 1) {
                System.out.println("enter your first name");
                String fname = bufferedReader.readLine();
                System.out.println("enter your last name");
                String lname = bufferedReader.readLine();
                System.out.println("enter your username");
                username = bufferedReader.readLine();
                System.out.println("enter your password");
                String password = bufferedReader.readLine();
                Request req = new SignupRequest(fname, lname, username, password);
                objectOutputStream.writeObject(req);
                objectOutputStream.flush();
                Response res =  (SignupResponse) objectInputStream.readObject();
                System.out.println(res);
                if (res.getCode() == ResponseCode.SUCCESS) {
                    isLogin = true;
                }
            } else if (ch == 2) {
                System.out.println("enter your registered username");
                username = bufferedReader.readLine();
                System.out.println("enter your password");
                String password = bufferedReader.readLine();
                Request req = new LoginRequest(username, password);
                objectOutputStream.writeObject(req);
                objectOutputStream.flush();
                Response res =  (LoginResponse) objectInputStream.readObject();
                System.out.println(res);
                if(res!=null)//for escaping null pointer exception
                if (res.getCode() == ResponseCode.SUCCESS) {
                    isLogin = true;
                }
            }
            else{
                System.out.println("Enter correct choice (1/2)");
            }
        }
        String finalUsername = username;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        sleep(60000);//60 seconds
                        Request req = new CheckAlert(finalUsername);
                        objectOutputStream.writeObject(req);
                        objectOutputStream.flush();
                        Response res = (CheckAlertResponse) objectInputStream.readObject();
                        System.out.println(res);
                        if(res.getCode()==ResponseCode.ALERT) {
                            try {
                                String filePath = "alert_audio.wav";
                                SimpleAudioPlayer audioPlayer = new SimpleAudioPlayer(filePath);
                                audioPlayer.play();
                                Thread.sleep(5000);//audio play for 5 seconds
                                audioPlayer.stop();
                            } catch (Exception ex) {
                                System.out.println("Error with playing sound.");
                                ex.printStackTrace();
                            }
                        }
                    }
                } catch (IOException | ClassNotFoundException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        int choice;
        do {

            System.out.println("Enter the choice you want to make");
            System.out.println("Enter 1 to add items in the inventory");
            System.out.println("Enter 2 to update item Inventory in the inventory");
            System.out.println("Enter 3 to remove items from the inventory");
            System.out.println("Enter 4 to search items in the inventory");
            System.out.println("Enter 5 to sort and display items in the inventory");
            System.out.println("enter 6 to Quit");
            choice = Integer.parseInt(bufferedReader.readLine());
            switch (choice) {
                case 1: {
                    System.out.println("Enter the ProductID of the product");
                    String productID = bufferedReader.readLine();
                    System.out.println("Enter the quantity of the product");
                    int quantity = Integer.parseInt(bufferedReader.readLine());
                    Request req = new AddItems(username, productID, quantity);
                    objectOutputStream.writeObject(req);
                    objectOutputStream.flush();
                    Response res = (AddItemsResponse) objectInputStream.readObject();
                    System.out.println(res);
                    break;
                }
                case 2: {
                    System.out.println("Enter the ProductID of the product which needs to be updated");
                    String productID = bufferedReader.readLine();
                    System.out.println("Enter the new quantity of the item");
                    int quantity = Integer.parseInt(bufferedReader.readLine());
                    Request req = new UpdateItems(username, productID, quantity);
                    objectOutputStream.writeObject(req);
                    objectOutputStream.flush();
                    Response res = (UpdateItemsResponse) objectInputStream.readObject();
                    System.out.println(res);
                    break;
                }
                case 3: {
                    System.out.println("enter the productID of the product  to be deleted");
                    String productID = bufferedReader.readLine();
                    Request req = new DeleteItem(username, productID);
                    objectOutputStream.writeObject(req);
                    objectOutputStream.flush();
                    Response res = (DeleteItemResponse) objectInputStream.readObject();
                    System.out.println(res);
                    break;
                }
                case 4: {
                    System.out.println("For searching based on Name press 1 else for category based searching press 2");
                    int n = Integer.parseInt(bufferedReader.readLine());
                    if (n == 1) {
                        System.out.println("Enter the product name");
                        String name = bufferedReader.readLine();
                        Request req = new SearchName(username, name);
                        objectOutputStream.writeObject(req);
                        objectOutputStream.flush();
                        Response res = (SearchNameResponse) objectInputStream.readObject();
                        System.out.println(res);
                    }
                    if (n != 1) {
                        System.out.println("Enter the category name");
                        String category = bufferedReader.readLine();
                        Request req = new SearchCategory(username, category);
                        objectOutputStream.writeObject(req);
                        objectOutputStream.flush();
                        Response res = (SearchCategoryResponse) objectInputStream.readObject();
                        System.out.println(res);
                    }
                    break;
                }
                case 5: {
                    System.out.println(
                            "For sorting and displaying the inventory with respect to price press 1 otherwise press 2");// for
                                                                                                                        // price
                                                                                                                        // priceORquant=1
                    int priceORquant = Integer.parseInt(bufferedReader.readLine());
                    if (priceORquant == 1) {
                        Request req = new DisplayPrice(username);
                        objectOutputStream.writeObject(req);
                        objectOutputStream.flush();
                        Response res = (DisplayPriceResponse) objectInputStream.readObject();
                        System.out.println(res);
                    }
                    if(priceORquant!=1) {
                        Request req = new DisplayQuant(username);
                        objectOutputStream.writeObject(req);
                        objectOutputStream.flush();
                        Response res = (DisplayQuantResponse) objectInputStream.readObject();
                        System.out.println(res);
                    }
                    break;
                }
                case 6: {
                    System.out.println("EXIT POINT");
                    break;
                }
                default: {
                    System.out.println("please enter a valid choice(1/2/3/4/5/6)");
                }
            }
        } while (choice != 6);
    }


    public Response getResponse(Socket socket) throws ClassNotFoundException {
        ObjectInputStream objectInputStream;
        try {
            objectInputStream=new ObjectInputStream(socket.getInputStream());
           return  (Response)objectInputStream.readObject();
        }catch (IOException e){
            System.out.println("Error in input Stream");
            return null;
        }
    }

}