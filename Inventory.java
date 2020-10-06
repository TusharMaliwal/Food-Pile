import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;

public class Inventory  {
   
    public void Additems(String username,BufferedReader bufferedReader) throws SQLException,IOException,ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/school";//school is the name of database 3306 is the port no. of mysql
        Connection connection = DriverManager.getConnection(url,"root","Tushar@123");
        String query0="create table'"+username+"' like inventory";
        PreparedStatement preStat= connection.prepareStatement(query0);
        preStat.executeUpdate();
        String query1 = "INSERT INTO'" + username + "'VALUES (?, ?, ?)";
        preStat = connection.prepareStatement(query1);
        System.out.println("enter the name of the item to be added in the inventory");
        String item = bufferedReader.readLine();
        System.out.println("enter the category of the item");
        String category = bufferedReader.readLine();
        System.out.println("enter the threshold value for this item");
        String threshold = bufferedReader.readLine();
        preStat.setString(1, item);
        preStat.setString(2, category);
        preStat.setString(3, threshold);
        preStat.executeUpdate();
        System.out.println("Item succesfully added in the inventory");
    }

    public void Update(String username,BufferedReader bufferedReader) throws SQLException,IOException,ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/school";//school is the name of database 3306 is the port no. of mysql
        Connection connection = DriverManager.getConnection(url,"root","Tushar@123");
        System.out.println("enter the name of the item to be updated in the inventory");
        String item = bufferedReader.readLine();
        System.out.println("To update item category press 1\n To update item threshold press 2 ");
        int t = Integer.parseInt(bufferedReader.readLine());
        if (t == 1) {
            System.out.println("Enter the new category of the item");
            String category = bufferedReader.readLine();
            String query2 = "update '" + username + "'Set category='" + category + "' where name='" + item
                    + "'";
            PreparedStatement preStat = connection.prepareStatement(query2);
            preStat.executeUpdate();
            System.out.println("category succesfully updated");
        }
        if (t == 2) {
            System.out.println("Enter the new threshold of the item");
            String threshold = bufferedReader.readLine();
            String query2 = "update '" +username + "'Set threshold='" + threshold + "' where name='" + item
                    + "'";
            PreparedStatement preStat = connection.prepareStatement(query2);
            preStat.executeUpdate();
            System.out.println("threshold succesfully updated");
        }

    }

    public void Delete( String username,BufferedReader bufferedReader) throws SQLException,IOException,ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/school";//school is the name of database 3306 is the port no. of mysql
        Connection connection = DriverManager.getConnection(url,"root","Tushar@123");
        System.out.println("enter the item to be deleted");
        String item = bufferedReader.readLine();
        String query3 = "Delete from '" + username + "'where name ='" + item + "'";
        PreparedStatement preStat = connection.prepareStatement(query3);
        preStat.executeUpdate();
        System.out.println("item succesfully deleted");
    }

    public void Search( String username,BufferedReader bufferedReader) throws SQLException,IOException,ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/school";//school is the name of database 3306 is the port no. of mysql
        Connection connection = DriverManager.getConnection(url,"root","Tushar@123");
        System.out.println("enter the name of the item to be searched for");
        String item = bufferedReader.readLine();
        String query4 = "Select * from'" + username + "'where name='" + item + "'";
        PreparedStatement preStat = connection.prepareStatement(query4);
        ResultSet result=preStat.executeQuery();
        String category=result.getString("category");
        String threshold=result.getString("threshold");
        System.out.println("Category -"+category);
        System.out.println("Threshold -"+threshold);
    }

    public void Display( String username,BufferedReader bufferedReader) throws SQLException,IOException,ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/school";//school is the name of database 3306 is the port no. of mysql
        Connection connection = DriverManager.getConnection(url,"root","Tushar@123");
        System.out.println("The items in the inventory are:");
        String query5 = "Select * from'" + username+ "'";
        PreparedStatement preStat = connection.prepareStatement(query5);
        ResultSet result = preStat.executeQuery();
        while(result.next()){
            String item=result.getString("item");
            String category=result.getString("category");
            String threshold=result.getString("threshold");
            System.out.println("Name -"+item);
            System.out.println("Category -"+category);
            System.out.println("Threshold -"+threshold);
        }
}

    }
