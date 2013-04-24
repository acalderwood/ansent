
import java.sql.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Alastair Calderwood
 */
public class NewClass {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ansent?zeroDateTimeBehavior=convertToNull", "root", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from anlagen");
            System.out.println(rs.next());
        } catch (Exception e) {
            e.printStackTrace();;
        }
    }
}
