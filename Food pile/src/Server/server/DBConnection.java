package Server.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {

    // Database credentials
    private static final String user   = "root";
    private static final String pass  = "root";

//    // Database credentials
//    private static final String user   = "root";
//    private static final String pass  = "Tushar@123";
    public static Connection connect() throws SQLException{
        System.out.println("Connecting to DB....");
        // Register the jdbc driver
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/school";//school is the name of database 3306 is the port no. of mysql
        Connection connection = DriverManager.getConnection(url,user,pass);
        return connection;
        }
        catch (ClassNotFoundException ex){
            System.err.println("Could not find jdbc driver.");
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
