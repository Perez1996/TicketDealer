package Model;
import Vistas.*;
import Controlador.*;
import Resources.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
    String url = "jdbc:mysql://localhost:3306/TicketDealer?useLegacyDatetimeCode=false&serverTimezone=UTC";
    String user = "TicketDealer";
    String pass = "1101";
    ResultSet rs ;
    Statement s ;
    Connection cnx;
        public Connect() throws SQLException {
         try {            

            Class.forName("com.mysql.cj.jdbc.Driver");
          cnx= DriverManager.getConnection(url, user, pass) ;

           
         } catch (ClassNotFoundException ex) {
            throw new ClassCastException(ex.getMessage());
         }
        }
        public Connection getConnection(){
        return cnx;
        }
}
