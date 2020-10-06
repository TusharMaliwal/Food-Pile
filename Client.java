import java.sql.SQLException;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

        Inventory inventory;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("enter 1 to sign up otherwise proceed to login");
          int ch=Integer.parseInt(bufferedReader.readLine());
          Scanner scanner=new Scanner(System.in);
          String username;
         if(ch==1){
            System.out.println("enter yor username you want to set up");
            username = scanner.nextLine();
            new Register().signup(username);
         }
        if(ch!=1){
            System.out.println("enter your registered username");
            username = scanner.nextLine();
            new LoginHandler().signin(username);
        }
        do{
             System.out.println("Enter the choice you want to make");
             System.out.println("Enter 1 to add items in the inventory");
             System.out.println("Enter 2 to update item details in the inventory");
             System.out.println("Enter 3 to remove items from the inventory");
             System.out.println("Enter 4 to search items in the inventory");
             System.out.println("Enter 5 to sort and display items in the inventory");
             System.out.println("enter 6 to Quit");
             int choice=Integer.parseInt(bufferedReader.readLine());  
                switch(choice){
                    case 1:
                    {
                        new Inventory.Additems(username,bufferedReader);
                        break;
                    }
                    case 2:
                    {
                        new Inventory.Update(username,bufferedReader);
                        break;
                    }
                    case 3:
                    {
                        new Inventory.Delete(username,bufferedReader);
                        break;
                    }
                    case 4:
                    {
                        new Inventory.Search(username,bufferedReader);
                        break;
                    }
                    case 5:
                    {
                        new Inventory.display(username,bufferedReader);
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

}