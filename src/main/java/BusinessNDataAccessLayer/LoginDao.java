/*
 * This class contains data access object for users.
 */
package BusinessNDataAccessLayer;

/**
 *
 * @author ndoni, tahiraj, muco
 */
import java.sql.*;
import PresentationLayer.DB;

public class LoginDao {

    /**
    *This class checks if the user is already signed up
    * in the database, so if the name and password match.
    */
    public static boolean validate(String name, String password) {
        boolean status = false;
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from login where UserName=? and Password=?");
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            status = rs.next();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

}
