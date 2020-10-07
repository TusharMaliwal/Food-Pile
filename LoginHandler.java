import java.sql.SQLException;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.util.Scanner;

public class LoginHandler{

        public void signin(String username)throws ClassNotFoundException, SQLException,FileNotFoundException{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/school";//school is the name of database 3306 is the port no. of mysql
            Connection connection = DriverManager.getConnection(url,"root","Tushar@123");
            int flag=0;
            Scanner scanner=new Scanner(System.in);
            System.out.println("enter your password");
            String password=scanner.nextLine();
            String query2 = "SELECT * FROM CLIENT;";
            PreparedStatement preStat=connection.prepareStatement(query2);
            ResultSet result = preStat.executeQuery();
            while(result.next()){
                if(username==result.getString("username"))
                {
                    if(password==result.getString("password"))
                    {
                        System.out.println("Login succesful");
                        break;
                    }
                    else
                    {
                        System.out.println("The password entered is wrong");
                        break;
                    }
                    
                }
                flag=1;
            }
                if(flag==0)
                    System.out.println("The username entered is not correct");    
        
        }

}
