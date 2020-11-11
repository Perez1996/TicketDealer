package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
    String url = "jdbc:mysql://uab1hyqinrvajxk0:054VN7orHN5TArwfoY0Z@buhzdu9tgyjhnapz0lv6-mysql.services.clever-cloud.com:3306/buhzdu9tgyjhnapz0lv6";
    String user = "uab1hyqinrvajxk0";
    String pass = "054VN7orHN5TArwfoY0Z";
    ResultSet rs ;
    Statement s ;
    Connection cnx;
        public Connect() throws SQLException {
         try {            

            Class.forName("com.mysql.cj.jdbc.Driver");
          cnx= DriverManager.getConnection(url, user, pass) ;

           
         } catch (Exception e) {
            
         }
        }
        public Connection getConnection(){
        return cnx;
        }
}
