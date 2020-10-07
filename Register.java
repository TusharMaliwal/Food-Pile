import java.sql.SQLException;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.util.Scanner;

public class Register{

    public void signup(String username) throws ClassNotFoundException, SQLException,FileNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/school";//school is the name of database 3306 is the port no. of mysql
        Connection connection = DriverManager.getConnection(url,"root","Tushar@123");
        String query0="create table (name varchar(30),username varchar(20) primary key,password varchar(20))";
        PreparedStatement preStat= connection.prepareStatement(query0);
        preStat.executeUpdate();
        //INPUT=" OR 1OR "1"="1
        //Select * from STUDENT where name ="" or 1 or "1"="1"
        String query1= "INSERT INTO CLIENT VALUES (?, ?, ?)";
        Scanner scanner=new Scanner(System.in);
        System.out.println("enter your name");
        String name=scanner.nextLine();
        System.out.println("enter your password");
        String password=scanner.nextLine();
        preStat= connection.prepareStatement(query1);
        preStat.setString(1,name);
        preStat.setString(2,username);
        preStat.setString(3,password);
        preStat.executeUpdate();    
    }

}